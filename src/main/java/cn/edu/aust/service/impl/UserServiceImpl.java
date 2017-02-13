package cn.edu.aust.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.aust.dao.UserMapper;
import cn.edu.aust.pojo.Solution;
import cn.edu.aust.pojo.User;
import cn.edu.aust.pojo.form.RankForm;
import cn.edu.aust.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource
	private UserMapper userMapper;

	@Override
	public User verificationUser(User user) {
		return this.userMapper.verificationUser(user);
	}

	@Override
	public User selectUserByUsername(String username) {
		return this.userMapper.selectUserByUsername(username);
	}

	@Override
	public int insertSelective(User user) {
		return this.userMapper.insertSelective(user);
	}

	@Override
	public List<Integer> FindACPronblemId(Solution acSolution) {
		return this.userMapper.FindACPronblemId(acSolution);
	}
	
	@Override
	public List<Integer> FindACBingPronblemId(Solution acSolution) {
		return this.userMapper.FindACBingPronblemId(acSolution);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {
		return this.userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User selectUserById(Integer userId) {
		return this.userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<RankForm> userRank() {
		//查询用户ID列表
		List<Integer> userIdList = this.userMapper.getRankUserId();
		List<RankForm> rankFormList = new ArrayList<>();
		//查询每个用户的提交信息
		for(int i = 0; i < userIdList.size(); i++){
			Integer userId = userIdList.get(i);
			RankForm rankForm = new RankForm();
			rankForm.setUserId(userId);
			//获取提交数量
			Integer submitNum = this.userMapper.getSubmitNum(userId);
			rankForm.setSubmit(submitNum);
			Integer acNum = this.userMapper.getAcNum(userId);
			rankForm.setAc(acNum);
			//计算通过率
			if(0 == submitNum && 0 == acNum){
				rankForm.setRatio("0%");
			}else{
				rankForm.setRatio((Math.round((float)acNum/(float)submitNum*100)) + "%");
			}
			User user = userMapper.selectByPrimaryKey(userId);
			rankForm.setUsername(user.getUsername());
			rankForm.setNickname(user.getNickname());
			rankForm.setBlog(user.getBlog());
			rankForm.setBlogname(user.getBlogname());
			rankFormList.add(rankForm);
		}
		//根据提交通过数的大小进行排名
		Collections.sort(rankFormList,new Comparator<RankForm>(){
            public int compare(RankForm arg0, RankForm arg1) {
                return arg0.getAc().compareTo(arg1.getAc());
            }
        });
		
		return rankFormList;
	}

	@Override
	public void updateLastTime(User userLogin) {
		this.userMapper.updateByPrimaryKeySelective(userLogin);
	}

	@Override
	public List<User> getUserList() {
		return this.userMapper.selectAllUser();
	}

	@Override
	public void deleteUserById(int[] userId) {
		for(int id : userId){
			System.out.println("要删除的ID:" + id);
			this.userMapper.deleteByPrimaryKey(id);
		}
	}
}

package cn.edu.aust.dao;

import java.util.List;

import cn.edu.aust.pojo.Solution;
import cn.edu.aust.pojo.User;
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User verificationUser(User user);

	User selectUserByUsername(String username);

	List<Integer> FindACPronblemId(Solution acSolution);
	
	List<Integer> FindACBingPronblemId(Solution acSolution);

	/**
	 * 获取提交列表中用户ID数组
	 * @return
	 */
	List<Integer> getRankUserId();

	/**
	 * 查询某个用户提交数量
	 * @param userId 
	 * @return
	 */
	Integer getSubmitNum(Integer userId);

	/**
	 * 查询某个用户提交通过数量
	 * @param userId 
	 * @return
	 */
	Integer getAcNum(Integer userId);
}
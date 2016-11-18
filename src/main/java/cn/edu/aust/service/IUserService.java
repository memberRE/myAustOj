package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.pojo.Solution;
import cn.edu.aust.pojo.User;
import cn.edu.aust.pojo.form.RankForm;

public interface IUserService {

	User verificationUser(User user);

	User selectUserByUsername(String username);

	int insertSelective(User user);

	List<Integer> FindACPronblemId(Solution acSolution);

	List<Integer> FindACBingPronblemId(Solution acSolution);

	int updateByPrimaryKeySelective(User user);

	User selectUserById(Integer userId);

	List<RankForm> userRank();

	/**
	 * 更新最后一次登录时间
	 * @param userLogin
	 */
	void updateLastTime(User userLogin);

}

/*package cn.edu.aust.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.edu.aust.pojo.User;

public class UserRealm implements Realm{

	
	public String getName() {
		return "myRealm";
	}

	public boolean supports(AuthenticationToken token) {
		//仅支持 UsernamePasswordToken 类型的 Token
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal(); //得到用户名
		String password = new String((char[])token.getCredentials()); //得到密码
		System.out.println("用户名：" + username + "  密码：" + password);
		if(!"zhang".equals(username)) {
			System.out.println("用户名错误");
		throw new UnknownAccountException(); //如果用户名错误
		}
		if(!"123456".equals(password)) {
			System.out.println("密码错误");
		throw new IncorrectCredentialsException(); //如果密码错误
		}
		
		User user = new User();
		user.setUserId(44);
		user.setUsername("zhang");
		user.setNickname("张三丰");
		user.setPassword("123456");
		
		 Subject currentUser = SecurityUtils.getSubject();  
	        if(null != currentUser){  
	            Session session = currentUser.getSession();  
	            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
	            if(null != session){  
	                session.setAttribute("userLogin", user);  
	            }  
	        }  
		//如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
		SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(username, password, getName());
		return sai;
	}


}
*/
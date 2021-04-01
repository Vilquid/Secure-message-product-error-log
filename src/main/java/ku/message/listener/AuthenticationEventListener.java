package ku.message.listener;

import ku.message.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationEventListener
{
	Logger logger = LoggerFactory.getLogger(AuthenticationEventListener.class);

	@Autowired
	private UserService userService;

	@EventListener
	public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event)
	{
		String username = (String) event.getAuthentication().getPrincipal();

		if (userService.isUsernameAvailable(username))
		{
			logger.warn("Failed login attempt [incorrect USERNAME]");
		}
		else
		{
			logger.warn("Failed login attempt [incorrect PASSWORD]");
		}
	}
}

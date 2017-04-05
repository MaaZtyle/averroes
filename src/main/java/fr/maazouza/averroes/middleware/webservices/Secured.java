package fr.maazouza.averroes.middleware.webservices;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;

@NameBinding
@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Secured {
	
	Role[] value() default {};
}
	


	




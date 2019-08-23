package fi.delta.delta

import fi.delta.delta.filters.AuthorizationFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class DeltaApplication {

	@Bean
	fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}

	@Bean
	fun authorizationFilter(): FilterRegistrationBean<AuthorizationFilter> {

		val registrationBean: FilterRegistrationBean<AuthorizationFilter> = FilterRegistrationBean()

		registrationBean.filter = AuthorizationFilter()
		registrationBean.addUrlPatterns("/admin/*")

		return registrationBean
	}



}

fun main(args: Array<String>) {
	runApplication<DeltaApplication>(*args)
}

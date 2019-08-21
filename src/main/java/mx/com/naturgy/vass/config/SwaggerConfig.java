package mx.com.naturgy.vass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  @author Michel Lopez
 *
 */

@Configuration
@EnableSwagger2
@Profile("swagger-enabled-for-dev-qa")
public class SwaggerConfig {

	/**
	 * @return Retorna los datos de contacto
	 */
	public static final ApiInfo API_INFO() {
		return new ApiInfoBuilder().title("Servicio Pagos")
				.description("API Documentación")
				.contact(new Contact("Edgar Duran", "https://www.naturgy.com/inicio", "edgar.duran@vasslatam.com"))
				.build();
	}
	
	
	/**
	 * @return Genera la conexion a SWAGGER
	 */
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").useDefaultResponseMessages(false).apiInfo(API_INFO()).select()
				.apis(RequestHandlerSelectors.any()).paths(Predicates.not(PathSelectors.regex("/error.*")))
				.paths(Predicates.not(PathSelectors.regex("/actuator.*"))).build();
	}

}

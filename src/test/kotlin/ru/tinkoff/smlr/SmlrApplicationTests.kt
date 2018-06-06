package ru.tinkoff.smlr

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import ru.tinkoff.smlr.model.repositories.LinkRepositoryTest

@RunWith(SpringRunner::class)
@SpringBootTest
@TestPropertySource(locations = ["classpath:repositories-test.properties"])
//@SpringBootApplication(scanBasePackageClasses = [SmlrApplication::class])
@WebAppConfiguration
class SmlrApplicationTests {

	@Test
	fun contextLoads() {
	}

}

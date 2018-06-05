package ru.tinkoff.smlr.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import ru.tinkoff.smlr.SmlrApplication

@TestPropertySource(locations = ["classpath:repositories-test.properties"])
@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringBootApplication(scanBasePackageClasses =  [SmlrApplication::class])
@DirtiesContext
abstract class AbstractRepositoryTest: AbstractTransactionalJUnit4SpringContextTests()
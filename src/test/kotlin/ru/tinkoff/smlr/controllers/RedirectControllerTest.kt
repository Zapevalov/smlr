package ru.tinkoff.smlr.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.tinkoff.smlr.SmlrApplicationTests
import ru.tinkoff.smlr.service.KeyMapperService


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootApplication(scanBasePackageClasses = [SmlrApplicationTests::class])
@TestPropertySource(locations = ["classpath:repositories-test.properties"])
@WebAppConfiguration
class RedirectControllerTest{
    @Autowired lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
        Mockito.`when`(service.getLink(PATH)).then { KeyMapperService.Get.Link(link = HEADER_VALUE) }
        Mockito.`when`(service.getLink(BAD_PATH)).then { KeyMapperService.Get.NotFound("/$BAD_PATH") }
    }

    private val PATH = "aAbBcCdD"
    private val REDIRECT_STATUS:Int = 302
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://tinkoff.ru"

    @Test fun controllerMustRedirectUsThenRequestIsSeccessful(){
        mockMvc.perform(MockMvcRequestBuilders.get("/$PATH"))
                .andExpect(MockMvcResultMatchers.status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val BAD_PATH = "/olololo"

    private val NOT_FOUND = 404

    @Test fun controllerMustReturn404ifBadKey(){
        mockMvc.perform(MockMvcRequestBuilders.get("/$BAD_PATH"))
                .andExpect(MockMvcResultMatchers.status().`is`(NOT_FOUND))
    }
}
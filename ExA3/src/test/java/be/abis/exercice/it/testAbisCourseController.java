package be.abis.exercice.it;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@WebMvcTest
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class testAbisCourseController {

	
	@Autowired
	MockMvc mockMvc;

	
	@Test
	public void testShowCoursePage() throws Exception {

	     mockMvc.perform(MockMvcRequestBuilders.get("/"))
	     	.andExpect(view().name("course"))
	     	.andDo(print());
	}
}

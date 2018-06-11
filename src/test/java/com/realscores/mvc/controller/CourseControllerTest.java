package com.realscores.mvc.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.realscores.obj.Course;
import com.realscores.service.course.ICourseService;
import com.realscores.obj.builder.CourseBuilder;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest extends BaseControllerTest
{
    @InjectMocks
    CourseController controller;
    
	@Mock
	ICourseService courseService;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp()
    {
      MockitoAnnotations.initMocks(this);
      mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    public void getCourses_happyPath() throws Exception{
      when(courseService.getAllCourses()).thenReturn(CourseBuilder.createCourseList(4));
      
      mockMvc.perform(get("/courses"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0]courseId", Matchers.is(1)))
        .andExpect(jsonPath("$.[0]name", Matchers.is("Course_1")))
        .andExpect(jsonPath("$.[1]courseId", Matchers.is(2)))
        .andExpect(jsonPath("$.[1]name", Matchers.is("Course_2")))
        .andExpect(jsonPath("$.[2]courseId", Matchers.is(3)))
        .andExpect(jsonPath("$.[2]name", Matchers.is("Course_3")))
        .andExpect(jsonPath("$.[3]courseId", Matchers.is(4)))
        .andExpect(jsonPath("$.[3]name", Matchers.is("Course_4")));
    }
    
    @Test
    public void getCourseId_happyPath() throws Exception{
      when(courseService.getCourseById(1)).thenReturn(CourseBuilder.createCourseWithHoles(1, 9));
      
      mockMvc.perform(get("/courses/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.courseId", Matchers.is(1)))
        .andExpect(jsonPath("$.name", Matchers.is("Course_1")))
        .andExpect(jsonPath("$.holes", Matchers.hasSize(9)))
        .andExpect(jsonPath("$.holes[0].holeId", Matchers.is(1)))
        .andExpect(jsonPath("$.holes[0].holeNumber", Matchers.is(1)))
        .andExpect(jsonPath("$.holes[0].par", Matchers.is(4)))
        .andExpect(jsonPath("$.holes[0].yards", Matchers.is(400)))
        .andExpect(jsonPath("$.holes[1].holeId", Matchers.is(2)))
        .andExpect(jsonPath("$.holes[1].holeNumber", Matchers.is(2)))
        .andExpect(jsonPath("$.holes[1].par", Matchers.is(5)))
        .andExpect(jsonPath("$.holes[1].yards", Matchers.is(600)))
        .andExpect(jsonPath("$.holes[2].holeId", Matchers.is(3)))
        .andExpect(jsonPath("$.holes[2].holeNumber", Matchers.is(3)))
        .andExpect(jsonPath("$.holes[2].par", Matchers.is(3)))
        .andExpect(jsonPath("$.holes[2].yards", Matchers.is(200)))
        .andExpect(jsonPath("$.holes[3].holeId", Matchers.is(4)))
        .andExpect(jsonPath("$.holes[3].holeNumber", Matchers.is(4)))
        .andExpect(jsonPath("$.holes[4].holeId", Matchers.is(5)))
        .andExpect(jsonPath("$.holes[4].holeNumber", Matchers.is(5)))
        .andExpect(jsonPath("$.holes[5].holeId", Matchers.is(6)))
        .andExpect(jsonPath("$.holes[5].holeNumber", Matchers.is(6)))
        .andExpect(jsonPath("$.holes[6].holeId", Matchers.is(7)))
        .andExpect(jsonPath("$.holes[6].holeNumber", Matchers.is(7)))
        .andExpect(jsonPath("$.holes[7].holeId", Matchers.is(8)))
        .andExpect(jsonPath("$.holes[7].holeNumber", Matchers.is(8)))
        .andExpect(jsonPath("$.holes[8].holeId", Matchers.is(9)))
        .andExpect(jsonPath("$.holes[8].holeNumber", Matchers.is(9))) ;
    }
    
    @Test
    public void createCourse_happyPath() throws Exception{
      when(courseService.addCourse(any(Course.class))).thenReturn(true);
      
      MvcResult mvcResult = mockMvc.perform(post("/courses")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonify(CourseBuilder.createCourse(1))))
        .andExpect(status().isCreated())
        .andReturn();
          
      Assert.assertEquals("http://localhost/courses/1", mvcResult.getResponse().getHeader("Location"));
    }
    
    @Test
    public void createCourse_alreadyExists() throws Exception{
      when(courseService.addCourse(any(Course.class))).thenReturn(false);
      
      mockMvc.perform(post("/courses")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonify(CourseBuilder.createCourse(1))))
        .andExpect(status().isConflict());
    }
    
    
    @Test
    public void updateCourse_happyPath() throws Exception{
      
      mockMvc.perform(put("/courses/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonify(CourseBuilder.createCourse(1))))
        .andExpect(status().isOk())
        .andReturn();
    }
    
    @Test
    public void updateCourse_mismatchIds() throws Exception{
      
      mockMvc.perform(put("/courses/2")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonify(CourseBuilder.createCourse(1))))
        .andExpect(status().isUnprocessableEntity())
        .andReturn();
    }
    
    @Test
    public void deleteCourse_happyPath() throws Exception{
      
      mockMvc.perform(delete("/courses/1"))
        .andExpect(status().isNoContent())
        .andReturn();
      
      verify(courseService).deleteCourse(1);
    }
}

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

import com.realscores.mvc.controller.PlayerController;
import com.realscores.obj.Player;
import com.realscores.service.player.IPlayerService;
import com.realscores.obj.builder.PlayerBuilder;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTest extends BaseControllerTest
{
  @InjectMocks
  PlayerController controller;
  
  @Mock
  IPlayerService playerService;    
  
  private MockMvc mockMvc;
  
  @Before
  public void setUp()
  {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }
  
  @Test
  public void getPlayers_happyPath() throws Exception{
    when(playerService.getAllPlayers()).thenReturn(PlayerBuilder.createPlayerList(4));
    
    mockMvc.perform(get("/players"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.[0]id", Matchers.is(1)))
      .andExpect(jsonPath("$.[0]name", Matchers.is("Player_1")))
      .andExpect(jsonPath("$.[1]id", Matchers.is(2)))
      .andExpect(jsonPath("$.[1]name", Matchers.is("Player_2")))
      .andExpect(jsonPath("$.[2]id", Matchers.is(3)))
      .andExpect(jsonPath("$.[2]name", Matchers.is("Player_3")))
      .andExpect(jsonPath("$.[3]id", Matchers.is(4)))
      .andExpect(jsonPath("$.[3]name", Matchers.is("Player_4")));
  }
  
  @Test
  public void getPlayerId_happyPath() throws Exception{
    when(playerService.getPlayerById(1)).thenReturn(PlayerBuilder.createPlayer(1));
    
    mockMvc.perform(get("/players/1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", Matchers.is(1)))
      .andExpect(jsonPath("$.name", Matchers.is("Player_1")));
  }
  
  @Test
  public void createPlayer_happyPath() throws Exception{
    when(playerService.addPlayer(any(Player.class))).thenReturn(true);
    
    MvcResult mvcResult = mockMvc.perform(post("/players")
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonify(PlayerBuilder.createPlayer(1))))
      .andExpect(status().isCreated())
      .andReturn();
        
    Assert.assertEquals("http://localhost/players/1", mvcResult.getResponse().getHeader("Location"));
  }
  
  @Test
  public void createPlayer_alreadyExists() throws Exception{
    when(playerService.addPlayer(any(Player.class))).thenReturn(false);
    
    mockMvc.perform(post("/players")
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonify(PlayerBuilder.createPlayer(1))))
      .andExpect(status().isConflict());
  }
  
  
  @Test
  public void updatePlayer_happyPath() throws Exception{
    
    mockMvc.perform(put("/players/1")
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonify(PlayerBuilder.createPlayer(1))))
      .andExpect(status().isOk())
      .andReturn();
  }
  
  @Test
  public void updatePlayer_mismatchIds() throws Exception{
    
    mockMvc.perform(put("/players/2")
      .contentType(MediaType.APPLICATION_JSON)
      .content(jsonify(PlayerBuilder.createPlayer(1))))
      .andExpect(status().isUnprocessableEntity())
      .andReturn();
  }
  
  @Test
  public void deletePlayer_happyPath() throws Exception{
    
    mockMvc.perform(delete("/players/1"))
      .andExpect(status().isNoContent())
      .andReturn();
    
    verify(playerService).deletePlayer(1);
  }
}

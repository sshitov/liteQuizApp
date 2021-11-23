package com.litequizapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.litequizapp.dto.AnswerDTO;
import com.litequizapp.dto.CategoryDTO;
import com.litequizapp.dto.QuestionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ControllerTests {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  private CategoryDTO category;
  private QuestionDTO question;
  private AnswerDTO answer;

  @BeforeEach
  public void setUp() {
    category = new CategoryDTO();
    question = new QuestionDTO();
    answer = new AnswerDTO();
  }

  @Test
  @DisplayName("GET all categories request returned status code 200")
  public void category_successGetAllRecords() throws Exception {
    getCategory()
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("GET category request by Id returned status code 200")
  public void category_successGetById() throws Exception {
    createNewCategory("New category");

    getCategory(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("New category"));
  }

  @Test
  @DisplayName("GET category request by Id returned correct validation message")
  public void category_failGetByNonExistingId() throws Exception {
    getCategory(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//body/div[3]/h2").string("Category with id: 1 - Not Found"));
  }

  @Test
  @DisplayName("PUT request with a null value for category title parameter returned correct validation message")
  public void category_failCreateByTitle() throws Exception {
    createNewCategory("")
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("The title can't be empty"));
  }

  @Test
  @DisplayName("PUT request with a correct value for category title parameter returned status code 3xx and correct body")
  public void category_successCreate() throws Exception {
    createNewCategory("New category")
        .andExpect(status().is3xxRedirection());

    getCategory(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("New category"));
  }

  @Test
  @DisplayName("POST request with a null value for category title parameter returned correct validation message")
  public void category_failUpdateByTitle() throws Exception {
    createNewCategory("New category");

    updateCategory(null, 1)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("The title can't be empty"));
  }

  @Test
  @DisplayName("POST request with a correct value for category title parameter returned status code 200 and correct body")
  public void category_successUpdate() throws Exception {
    createNewCategory("New category");

    updateCategory("Update category", 1)
        .andExpect(status().is3xxRedirection());

    getCategory(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("Update category"));
  }

  @Test
  @DisplayName("DELETE request for category returned status code 200")
  public void category_successDelete() throws Exception {
    createNewCategory("New category");

    deleteCategory(1)
        .andExpect(status().is3xxRedirection());
  }

  @Test
  @DisplayName("DELETE request for category returned correct validation message")
  public void category_failDeleteByNonExistingId() throws Exception {
    deleteCategory(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//body/div[3]/h2").string("Category with id: 1 - Not Found"));
  }

  @Test
  @DisplayName("GET all questions request returned status code 200")
  public void question_successGetAllRecords() throws Exception {
    getQuestion()
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("GET question request by Id returned status code 200")
  public void question_successGetById() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);

    getQuestion(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("New question"));
  }

  @Test
  @DisplayName("GET question request by Id returned correct validation message")
  public void question_failGetByNonExistingId() throws Exception {
    getQuestion(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//body/div[3]/h2").string("Question with id: 1 - Not Found"));
  }

  @Test
  @DisplayName("PUT request with a null value for question title parameter returned correct validation message")
  public void question_failCreateByTitle() throws Exception {
    createNewQuestion(" ", 1L)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("The title can't be empty"));
  }

  @Test
  @DisplayName("PUT request with a null value for question categoryId parameter returned correct validation message")
  public void question_failCreateByCategoryId() throws Exception {
    createNewQuestion("New question", null)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("Related entry id can't be empty"));
  }

  @Test
  @DisplayName("PUT request with a correct value for all question parameters returned status code 3xx and correct body")
  public void question_successCreate() throws Exception {
    createNewCategory("New category");

    createNewQuestion("New question", 1L)
        .andExpect(status().is3xxRedirection());

    getQuestion(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("New question"))
        .andExpect(xpath("//tr[3]/td[3]").string("1"));
  }

  @Test
  @DisplayName("POST request with a null value for question title parameter returned correct validation message")
  public void question_failUpdateByTitle() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);

    updateQuestion(null, 1L, 1)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("The title can't be empty"));
  }

  @Test
  @DisplayName("POST request with a null value for question categoryId parameter returned correct validation message")
  public void question_failUpdateByCategoryId() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);

    updateQuestion("Update question", null, 1)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("Related entry id can't be empty"));
  }

  @Test
  @DisplayName("POST request with a correct value for all question parameters returned status code 200 and correct body")
  public void question_successUpdate() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);

    updateQuestion("Update question", 1L, 1)
        .andExpect(status().is3xxRedirection());

    getQuestion(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("Update question"))
        .andExpect(xpath("//tr[3]/td[3]").string("1"));
  }

  @Test
  @DisplayName("DELETE request for question returned status code 200")
  public void question_successDelete() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);

    deleteQuestion(1)
        .andExpect(status().is3xxRedirection());
  }

  @Test
  @DisplayName("DELETE request for question returned correct validation message")
  public void question_failDeleteByNonExistingId() throws Exception {
    deleteQuestion(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//body/div[3]/h2").string("Question with id: 1 - Not Found"));
  }

  @Test
  @DisplayName("GET all answers request returned status code 200")
  public void answer_successGetAllRecords() throws Exception {
    getAnswer()
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("GET answer request by Id returned status code 200")
  public void answer_successGetById() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);
    createNewAnswer("New answer", 1L);

    getAnswer(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("New answer"));
  }

  @Test
  @DisplayName("GET answer request by Id returned correct validation message")
  public void answer_failGetByNonExistingId() throws Exception {
    getAnswer(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//body/div[3]/h2").string("Answer with id: 1 - Not Found"));
  }

  @Test
  @DisplayName("PUT request with a null value for answer title parameter returned correct validation message")
  public void answer_failCreateByTitle() throws Exception {
    createNewAnswer(" ", 1L)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("The title can't be empty"));
  }

  @Test
  @DisplayName("PUT request with a null value for answer questionId parameter returned correct validation message")
  public void answer_failCreateByQuestionId() throws Exception {
    createNewAnswer("New answer", null)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("Related entry id can't be empty"));
  }

  @Test
  @DisplayName("PUT request with a correct value for all answer parameters returned status code 200 and correct body")
  public void answer_successCreate() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);
    createNewAnswer("New answer", 1L)
        .andExpect(status().is3xxRedirection());

    getAnswer(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("New answer"))
        .andExpect(xpath("//tr[3]/td[3]").string("1"));
  }

  @Test
  @DisplayName("POST request with a null value for answer title parameter returned correct validation message")
  public void answer_failUpdateByTitle() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);
    createNewAnswer("New answer", 1L);

    updateAnswer(null, 1L, 1)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("The title can't be empty"));
  }

  @Test
  @DisplayName("POST request with a null value for answer questionId parameter returned correct validation message")
  public void answer_failUpdateByQuestionId() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);
    createNewAnswer("New answer", 1L);

    updateAnswer("Update answer", null, 1)
        .andExpect(status().isOk())
        .andExpect(xpath("//form/p").string("Related entry id can't be empty"));
  }

  @Test
  @DisplayName("POST request with a correct value for all answer parameters returned status code 200 and correct body")
  public void answer_successUpdate() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);
    createNewAnswer("New answer", 1L);
    updateAnswer("Update answer", 1L, 1)
        .andExpect(status().is3xxRedirection());

    getAnswer(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//tr[1]/td[3]").string("1"))
        .andExpect(xpath("//tr[2]/td[3]").string("Update answer"))
        .andExpect(xpath("//tr[3]/td[3]").string("1"));
  }

  @Test
  @DisplayName("DELETE request for answer returned status code 200")
  public void answer_successDelete() throws Exception {
    createNewCategory("New category");
    createNewQuestion("New question", 1L);
    createNewAnswer("New answer", 1L);

    deleteAnswer(1)
        .andExpect(status().is3xxRedirection());
  }

  @Test
  @DisplayName("DELETE request for answer returned correct validation message")
  public void answer_failDeleteByNonExistingId() throws Exception {
    deleteAnswer(1)
        .andExpect(status().isOk())
        .andExpect(xpath("//body/div[3]/h2").string("Answer with id: 1 - Not Found"));
  }

  public ResultActions getCategory() throws Exception {
    return mockMvc.perform(get("/admin/category"));
  }

  public ResultActions getCategory(int id) throws Exception {
    return mockMvc.perform(get("/admin/category/{id}", id));
  }

  public ResultActions createNewCategory(String title) throws Exception {
    category.setTitle(title);

    return mockMvc.perform(putForm("/admin/category/create", category));
  }

  public ResultActions updateCategory(String title, int id) throws Exception {
    category.setTitle(title);
    String updateUrl = String.format("/admin/category/%s/update", id);

    return mockMvc.perform(postForm(updateUrl, category));

  }

  public ResultActions deleteCategory(int id) throws Exception {
    return mockMvc.perform(delete("/admin/category/{id}/delete", id));
  }

  public ResultActions getQuestion() throws Exception {
    return mockMvc.perform(get("/admin/question"));
  }

  public ResultActions getQuestion(int id) throws Exception {
    return mockMvc.perform(get("/admin/question/{id}", id));
  }

  public ResultActions createNewQuestion(String title, Long categoryId) throws Exception {
    question.setTitle(title);
    question.setCategoryId(categoryId);

    return mockMvc.perform(putForm("/admin/question/create", question));
  }

  public ResultActions updateQuestion(String title, Long categoryId, int id) throws Exception {
    question.setTitle(title);
    question.setCategoryId(categoryId);
    String updateUrl = String.format("/admin/question/%s/update", id);

    return mockMvc.perform(postForm(updateUrl, question));
  }

  public ResultActions deleteQuestion(int id) throws Exception {
    return mockMvc.perform(delete("/admin/question/{id}/delete", id));
  }

  public ResultActions getAnswer() throws Exception {
    return mockMvc.perform(get("/admin/answer"));
  }

  public ResultActions getAnswer(int id) throws Exception {
    return mockMvc.perform(get("/admin/answer/{id}", id));
  }

  public ResultActions createNewAnswer(String title, Long questionId) throws Exception {
    answer.setTitle(title);
    answer.setQuestionId(questionId);

    return mockMvc.perform(putForm("/admin/answer/create", answer));
  }

  public ResultActions updateAnswer(String title, Long questionId, int id) throws Exception {
    answer.setTitle(title);
    answer.setQuestionId(questionId);
    String updateUrl = String.format("/admin/answer/%s/update", id);

    return mockMvc.perform(postForm(updateUrl, answer));
  }


  public ResultActions deleteAnswer(int id) throws Exception {
    return mockMvc.perform(delete("/admin/answer/{id}/delete", id));
  }

}

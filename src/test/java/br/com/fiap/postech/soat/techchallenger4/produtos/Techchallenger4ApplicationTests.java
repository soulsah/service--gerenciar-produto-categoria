package br.com.fiap.postech.soat.techchallenger4.produtos;

import br.com.fiap.postech.soat.techchallenger4.produtos.controller.ProdutoController;
import br.com.fiap.postech.soat.techchallenger4.produtos.enums.Categoria;
import br.com.fiap.postech.soat.techchallenger4.produtos.exception.ProdutoNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.produtos.records.ProdutoRecord;
import br.com.fiap.postech.soat.techchallenger4.produtos.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

	@InjectMocks
	private ProdutoController produtoController;

	@Mock
	private ProdutoService produtoService;

	private ProdutoRecord produtoRecord;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		produtoRecord = new ProdutoRecord(1L, "Produto 1", Categoria.BEBIDA, 10.0, "Descrição", "Outro campo");
	}

	@Test
	void getAllProdutos_ShouldReturnListOfProdutos() throws ProdutoNotFoundException {
		when(produtoService.findAllProdutos()).thenReturn(Collections.singletonList(produtoRecord));

		ResponseEntity<List<ProdutoRecord>> response = produtoController.getAllProdutos();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		assertEquals("Produto 1", response.getBody().get(0).nome());
	}

	@Test
	void getProdutoById_ShouldReturnProduto_WhenFound() throws ProdutoNotFoundException {
		when(produtoService.findProdutoById(1L)).thenReturn(produtoRecord);

		ResponseEntity<ProdutoRecord> response = produtoController.getProdutoById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Produto 1", response.getBody().nome());
	}

	@Test
	void getProdutoById_ShouldReturnNotFound_WhenNotFound() throws ProdutoNotFoundException {
		when(produtoService.findProdutoById(2L)).thenThrow(new ProdutoNotFoundException());

		ResponseEntity<ProdutoRecord> response = produtoController.getProdutoById(2L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void createProduto_ShouldReturnCreatedProduto() {
		when(produtoService.createProduto(any(ProdutoRecord.class))).thenReturn(produtoRecord);

		ResponseEntity<ProdutoRecord> response = produtoController.createProduto(produtoRecord);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Produto 1", response.getBody().nome());
	}

	@Test
	void updateProduto_ShouldReturnUpdatedProduto_WhenFound() throws ProdutoNotFoundException {
		when(produtoService.updateProduto(eq(1L), any(ProdutoRecord.class))).thenReturn(produtoRecord);

		ResponseEntity<ProdutoRecord> response = produtoController.updateProduto(1L, produtoRecord);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Produto 1", response.getBody().nome());
	}

	@Test
	void updateProduto_ShouldReturnNotFound_WhenNotFound() throws ProdutoNotFoundException {
		when(produtoService.updateProduto(eq(2L), any(ProdutoRecord.class))).thenThrow(new ProdutoNotFoundException());

		ResponseEntity<ProdutoRecord> response = produtoController.updateProduto(2L, produtoRecord);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void deleteProduto_ShouldReturnNoContent() {
		doNothing().when(produtoService).deleteProduto(1L);

		ResponseEntity<Void> response = produtoController.deleteProduto(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(produtoService, times(1)).deleteProduto(1L);
	}
}

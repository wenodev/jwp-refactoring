package kitchenpos.product;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import kitchenpos.menu.domain.Price;
import kitchenpos.menu.exception.PriceException;
import kitchenpos.product.application.ProductService;
import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;

@DisplayName("상품 서비스 테스트")
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	ProductRequest 양념치킨_요청;
	Product 양념치킨;

	@BeforeEach
	void setUp() {
		양념치킨_요청 = 상품생성_요청(new BigDecimal(10000), "양념 치킨");
		양념치킨 = 양념치킨_요청.toProduct();
	}

	@DisplayName("상품을 생성한다.")
	@Test
	void 상품_생성한다() {
		given(productRepository.save(any())).willReturn(양념치킨);

		ProductResponse created = productService.create(양념치킨_요청);

		상품_생성_확인(created, 양념치킨_요청);
	}

	@DisplayName("상품 생성 시 - 상품의 가격이 Null이면 생성할 수 없다.")
	@Test
	void 상품_생성_시_상품의_가격이_NULL_이면_생성할_수_없다() {
		assertThatThrownBy(() -> {
			양념치킨_요청 = ProductServiceTest.상품생성_요청(null, "양념 치킨");
			productService.create(양념치킨_요청);
		}).isInstanceOf(PriceException.class);
	}

	@DisplayName("상품 생성 시 - 상품의 가격이 0원 이상이여야 한다")
	@Test
	void 상품_생성_시_상품의_가격이_0원_초과하여야_한다() {
		assertThatThrownBy(() -> {
			양념치킨_요청 = ProductServiceTest.상품생성_요청(new BigDecimal(-5), "양념 치킨");
			productService.create(양념치킨_요청);
		}).isInstanceOf(PriceException.class);
	}

	@DisplayName("상품의 리스트를 조회한다.")
	@Test
	void 상품의_리스트를_조회한다() {
		given(productRepository.findAll()).willReturn(Arrays.asList(양념치킨));

		List<ProductResponse> selected = productService.list();

		상품_리스트_조회_확인(selected);
	}

	private void 상품_리스트_조회_확인(List<ProductResponse> selected) {
		assertThat(selected).isNotEmpty();
	}

	private void 상품_생성_확인(ProductResponse created, ProductRequest expected) {
		assertThat(created.getName()).isEqualTo(expected.getName());
		assertThat(created.getPrice()).isEqualTo(expected.getPrice());
	}

	public static ProductRequest 상품생성_요청(BigDecimal price, String name) {
		ProductRequest productRequest = new ProductRequest(name, price);
		return productRequest;
	}
}

package kitchenpos.menu.dto;

import kitchenpos.menuGroup.dto.MenuGroupResponse;
import kitchenpos.menu.domain.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MenuResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private MenuGroupResponse menuGroupResponse;
    private List<MenuProductResponse> menuProducts;

    public MenuResponse() {
    }

    public MenuResponse(Long id, String name, BigDecimal price, MenuGroupResponse menuGroupResponse, List<MenuProductResponse> menuProducts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupResponse = menuGroupResponse;
        this.menuProducts = menuProducts;
    }

    public static MenuResponse of(Menu menu) {
        return new MenuResponse(menu.getId(), menu.getName(),
                menu.getPrice(), MenuGroupResponse.of(menu.getMenuGroup()),
                menu.getMenuProducts().getMenuProducts().stream()
                .map(it -> MenuProductResponse.of(it))
                .collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<MenuProductResponse> getMenuProducts() {
        return menuProducts;
    }
}

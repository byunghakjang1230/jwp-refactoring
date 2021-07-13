package kitchenpos.menu.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.menu.exception.MenuNotFoundException;
import kitchenpos.menugroup.application.MenuGroupService;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.product.application.ProductService;
import kitchenpos.product.domain.Product;

@Service
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuGroupService menuGroupService;
    private final ProductService productService;

    @Autowired
    public MenuService(final MenuRepository menuRepository, final MenuGroupService menuGroupService,
                       final ProductService productService) {
        this.menuRepository = menuRepository;
        this.menuGroupService = menuGroupService;
        this.productService = productService;
    }

    public MenuResponse create(final MenuRequest menuRequest) {
        MenuGroup menuGroup = menuGroupService.findById(menuRequest.getMenuGroupId());
        Menu menu = menuRepository.save(new Menu(menuRequest.getName(), menuRequest.getPrice(), menuGroup));
        menuRequest.getMenuProductRequests()
                .forEach(menuProductRequest -> menu.addMenuProduct(createMenuProduct(menu, menuProductRequest)));
        menu.validateMenuPrice();
        return MenuResponse.of(menu);
    }

    public List<MenuResponse> findAllMenu() {
        return menuRepository.findAll()
                .stream()
                .map(MenuResponse::of)
                .collect(Collectors.toList());
    }

    public Menu findMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("조회된 메뉴가 없습니다. 입력 ID : " + id));
    }

    private MenuProduct createMenuProduct(Menu menu, MenuProductRequest menuProductRequest) {
        Product product = productService.findById(menuProductRequest.getProductId());
        return new MenuProduct(menu, product, menuProductRequest.getQuantity());
    }
}

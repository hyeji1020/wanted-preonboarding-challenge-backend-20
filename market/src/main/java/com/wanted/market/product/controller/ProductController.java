package com.wanted.market.product.controller;

import com.wanted.market.member.dto.CustomUserDetails;
import com.wanted.market.product.dto.ProductDetailResponseDto;
import com.wanted.market.product.dto.ProductRequestDto;
import com.wanted.market.product.dto.ProductResponseDto;
import com.wanted.market.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /* 제품 등록 */
    @PostMapping
    public ResponseEntity<ProductResponseDto> registerProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails, @Valid @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.registerProduct(customUserDetails.getUsername(), productRequestDto);
        return ResponseEntity.ok(productResponseDto);
    }

    /* 제품 상세 조회*/
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Integer id) {
        // 회원, with 구매내역
        if(customUserDetails != null){
            ProductDetailResponseDto productDetailResponseDto = productService.findDetailProductById(customUserDetails.getUsername(), id);
            return ResponseEntity.ok().body(productDetailResponseDto);
        }
        // 비회원
        ProductResponseDto productResponseDto = productService.findById(customUserDetails.getUsername(), id);
        return ResponseEntity.ok().body(productResponseDto);
    }

    /* 제품 목록 조회 */
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProductList() {
        List<ProductResponseDto> productResponseDtos = productService.findAll();
        return ResponseEntity.ok().body(productResponseDtos);
    }
}

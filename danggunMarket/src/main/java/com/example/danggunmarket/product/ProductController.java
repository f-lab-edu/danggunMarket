package com.example.danggunmarket.product;

import com.example.danggunmarket.common.auth.LoggedInMember;
import com.example.danggunmarket.common.pageDto.PageResultDto;
import com.example.danggunmarket.product.dto.AddProductRequest;
import com.example.danggunmarket.product.dto.EditProductRequest;
import com.example.danggunmarket.product.dto.GetProductDetailResponse;
import com.example.danggunmarket.product.dto.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/v1/products")
    public ResponseEntity<GetProductResponse> addProduct(@AuthenticationPrincipal LoggedInMember member,
                                                         @RequestPart("pictureFile") MultipartFile imageFile,
                                                         @RequestPart("info") AddProductRequest productRequest) throws IOException {
        GetProductResponse response =
                productService.addProductWithLocalImage(member, imageFile, productRequest);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/v1/products")
    public ResponseEntity<PageResultDto<List<GetProductResponse>>> getProducts(@PageableDefault(size = 20) Pageable pageable) {
        PageResultDto<List<GetProductResponse>> response = productService.getProductResponses(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("productId").descending())
        );

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/v1/products/{id}")
    public ResponseEntity<GetProductDetailResponse> getProductDetail(@PathVariable("id") long id) {
        GetProductDetailResponse response = productService.getProductDetailResponse(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/v1/products/{id}")
    public ResponseEntity<GetProductDetailResponse> modifyProduct(@PathVariable("id") long id,
                                                                  @RequestBody EditProductRequest request) {
        GetProductDetailResponse response = productService.editProduct(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/v1/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}

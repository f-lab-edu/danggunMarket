package com.example.danggunmarket.product;

import com.example.danggunmarket.common.auth.LoggedInMember;
import com.example.danggunmarket.common.pageDto.PageResultDto;
import com.example.danggunmarket.member.MemberEntity;
import com.example.danggunmarket.member.MemberService;
import com.example.danggunmarket.product.dto.AddProductRequest;
import com.example.danggunmarket.product.dto.EditProductRequest;
import com.example.danggunmarket.product.dto.GetProductDetailResponse;
import com.example.danggunmarket.product.dto.GetProductResponse;
import com.example.danggunmarket.product.exception.ProductErrorCode;
import com.example.danggunmarket.product.exception.ProductNotFoundException;
import com.example.danggunmarket.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MemberService memberService;

    @Transactional
    public GetProductResponse addProductWithLocalImage(LoggedInMember loggedInMember,
                                                       MultipartFile imageFile,
                                                       AddProductRequest productRequest) throws IOException {

        ProductEntity newProduct = ProductMapper.INSTANCE.toEntity(productRequest);
        MemberEntity member = memberService.findById(loggedInMember.getId());

        newProduct.setPicturePath("default");
        newProduct.setMember(member);

        ProductEntity product = productRepository.save(newProduct);
        member.addProduct(product);

        GetProductResponse response = ProductMapper.INSTANCE.toGetDto(product);
        response.setSeller(loggedInMember.getNickname());

        return response;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "mainPage", keyGenerator = "paginationKeyGenerator")
    public PageResultDto<List<GetProductResponse>> getProductResponses(PageRequest pageable) {

        Page<ProductEntity> pageOfProducts = productRepository.findAll(pageable);

        List<GetProductResponse> products = pageOfProducts.getContent()
                .stream()
                .map(product -> {
                    GetProductResponse getProduct = ProductMapper.INSTANCE.toGetDto(product);
                    getProduct.setSeller(product.getMember().getNickname());
                    return getProduct;
                }).toList();

        return PageResultDto.<List<GetProductResponse>>builder()
                .result(products)
                .pageNumber(pageOfProducts.getNumber())
                .pageSize(pageOfProducts.getSize())
                .hasNext(pageOfProducts.hasNext())
                .hasPrevious(pageOfProducts.hasPrevious())
                .build();
    }

    @Transactional(readOnly = true)
    public GetProductDetailResponse getProductDetailResponse(long id) {

        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ProductErrorCode.NOT_FOUND_PRODUCT));

        GetProductDetailResponse response = ProductMapper.INSTANCE.toGetDetailDto(product);
        response.setSeller(product.getMember().getNickname());

        return response;
    }

    @Transactional
    public GetProductDetailResponse editProduct(long id, EditProductRequest request) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ProductErrorCode.NOT_FOUND_PRODUCT));

        product.update(request.getName(), request.getDescription(), request.getPrice());

        return ProductMapper.INSTANCE.toGetDetailDto(product);
    }

    @Transactional
    public void deleteProduct(long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ProductErrorCode.NOT_FOUND_PRODUCT));

        product.setDeleted();
    }

    public boolean existProductById(long id) {
        return productRepository.existsById(id);
    }

    public boolean matchSellerByUser(long id, LoggedInMember loggedInMember) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(ProductErrorCode.NOT_FOUND_PRODUCT))
                .getMember().getMemberId() == loggedInMember.getId();
    }
}

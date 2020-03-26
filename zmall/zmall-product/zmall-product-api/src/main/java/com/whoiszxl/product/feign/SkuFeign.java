package com.whoiszxl.product.feign;

import com.whoiszxl.common.entity.Result;
import com.whoiszxl.product.entity.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "zmall-product-web")
@RequestMapping("/product")
public interface SkuFeign {

    @PostMapping("/sku/spu/{spuId}")
    List<Sku> findSkuListBySpuId(@PathVariable("spuId") String spuId);

    @GetMapping("/sku/{id}")
    Result<Sku> findById(@PathVariable("id") String id);
}

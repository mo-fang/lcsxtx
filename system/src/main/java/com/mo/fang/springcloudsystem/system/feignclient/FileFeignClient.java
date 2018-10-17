//package com.mo.fang.springcloudsystem.system.feignclient;
//
//
//import com.mo.fang.springcloudsystem.system.contracts.FileFeignConfiguration;
//import feign.RequestLine;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//import utils.Result;
//
//@FeignClient(name = "microservice-procider-file", configuration = FileFeignConfiguration.class)
//public interface FileFeignClient {
//    @RequestMapping(method = RequestMethod.POST ,value = "/uploadPic.html",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    String upload(@RequestPart(value = "file") MultipartFile file, @RequestParam("path")String path);
//
//}

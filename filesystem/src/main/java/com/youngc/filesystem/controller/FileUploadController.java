package com.youngc.filesystem.controller;

import com.youngc.filesystem.service.IFileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;

@Api(tags = "文件服务接口")
@RestController
public class FileUploadController implements IBaseController {

    private final IFileUploadService fileUploadService;

    public FileUploadController(@Autowired IFileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("file/upload")
    @ApiOperation(value = "文件上传")
    public ResponseEntity uploadFile(
        @ApiParam(value = "文件夹") @RequestParam(required = false) String prefix,
        @ApiParam(value = "文件列表", required = true) @RequestParam("file") MultipartFile[] file,
        @ApiParam(value = "过期时间（分钟）", example = "60") @RequestParam(required = false) Long expireTime,
        @ApiParam(value = "是否返回完整地址") @RequestParam(required = false) Boolean flag
    ) {
        if (null == file || file.length == 0) return ResponseEntity.ok(errorJson("文件列表为空！"));
        flag = null == flag ? false : flag;
        return ResponseEntity.ok(successJson(fileUploadService.uploadFile(prefix, Arrays.asList(file), null == expireTime ? -1 : expireTime, flag)));
    }

    @PostMapping("file/delete")
    public ResponseEntity delete(
        @ApiParam(value = "文件名称", required = true) @RequestParam("name") String name
    ) {
        return fileUploadService.deleteFile(name) > 0 ? ResponseEntity.ok(successJson()) : ResponseEntity.ok(errorJson());
    }

}

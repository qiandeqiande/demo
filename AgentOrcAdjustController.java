package com.mli.cms.commission.module.performance.controller;


import com.mli.cms.commission.module.performance.dto.AgentOrcAdjustDetailDTO;
import com.mli.cms.commission.module.performance.dto.AgentOrcAdjustTryCalcDTO;
import com.mli.cms.commission.module.performance.mapper.AgentOrcAdjustDetailMapper;
import com.mli.cms.commission.module.performance.mapper.AgentOrcAdjustTryCalcMapper;
import com.mli.cms.commission.module.performance.service.AgentOrcAdjustService;
import com.mli.cms.commission.module.performance.vo.*;
import com.mli.cms.kernel.dto.ReviewDTO;
import com.mli.cms.kernel.page.PageResult;
import com.mli.cms.kernel.utils.ExcelUtils;
import com.mli.cms.kernel.utils.FileUtils;
import com.mli.cms.kernel.utils.PageUtils;
import com.mli.csmo.core.lang.exception.BusinessValidationException;
import com.mli.csmo.data.view.model.Criterion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

/**
 * 業績調整(AgentOrcAdjust)表控制层4
 *
 * @author deqian@eisgroup.com
 * @since 2025-06-30
 */
@Tag(name = "AgentOrcAdjust", description = "業績調整3")
@RestController
@RequestMapping("/api/agentOrcAdjust")
public class AgentOrcAdjustController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentOrcAdjustController.class.getName());
    /**
     * 服务对象
     * 33
     */
    @Autowired
    private AgentOrcAdjustService agentOrcAdjustService;

    /**
     * 業績調整 分页查询
     *
     * @param page      頁碼，從 1 開始
     * @param size      每頁條數
     * @param criterion 篩選條件
     */
    @Operation(summary = "業績調整分页查询")
    @Description("業績調整分页查询")
    @PostMapping(value = "/queryAgentOrcAdjustPage/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({@Parameter(name = "page", description = "頁碼，從 1 開始", required = true), @Parameter(name = "size", description = "每頁條數", required = true)})
    public ResponseEntity<PageResult<AgentOrcAdjustVO>> queryAgentOrcAdjustPage(@RequestParam(required = true) int page, @RequestParam(required = true) int size, @RequestBody Criterion criterion) {
        var pageResult = agentOrcAdjustService.queryAgentOrcAdjustPage(criterion, PageUtils.of(page, size, criterion.getSorts()));
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 業績調整 分页查询
     *
     * @param page      頁碼，從 1 開始
     * @param size      每頁條數
     * @param criterion 篩選條件
     */
    @Operation(summary = "業績調整分页查询")
    @Description("業績調整分页查询")
    @PostMapping(value = "/queryAgentOrcAdjustPage/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({@Parameter(name = "page", description = "頁碼，從 1 開始", required = true), @Parameter(name = "size", description = "每頁條數", required = true)})
    public ResponseEntity<PageResult<AgentOrcAdjustVO>> queryAgentOrcAdjustPageV2(@RequestParam(required = true) int page, @RequestParam(required = true) int size, @RequestBody Criterion criterion) {
        var pageResult = agentOrcAdjustService.queryAgentOrcAdjustPage(criterion, PageUtils.of(page, size, criterion.getSorts()));
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 業績調整歷史分页查询
     *
     * @param page      頁碼，從 1 開始
     * @param size      每頁條數
     * @param criterion 篩選條件
     */
    @Operation(summary = "業績調整歷史分页查询")
    @Description("業績調整歷史分页查询")
    @PostMapping(value = "/queryAgentOrcAdjustHistoryPage/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({@Parameter(name = "page", description = "頁碼，從 1 開始", required = true), @Parameter(name = "size", description = "每頁條數", required = true)})
    public ResponseEntity<PageResult<AgentOrcAdjustHistoryVO>> queryAgentOrcAdjustHistoryPage(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int size,
            @RequestBody Criterion criterion) {
        var pageResult = agentOrcAdjustService.queryAgentOrcAdjustHistoryPage(criterion, PageUtils.of(page, size, criterion.getSorts()));
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 保單信息查詢
     *
     * @param criterion 篩選條件
     */
    @Operation(summary = "保單信息查詢")
    @Description("保單信息查詢")
    @PostMapping(value = "/queryPolicyList/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgentOrcAdjustPolicyVo>> queryPolicyList(@RequestBody Criterion criterion) {
        var pageResult = agentOrcAdjustService.queryPolicyList(criterion);
        return ResponseEntity.ok(pageResult);
    }
    /**
     * 保單信息查詢
     *
     * @param criterion 篩選條件
     */
    @Operation(summary = "保單信息查詢")
    @Description("保單信息查詢")
    @PostMapping(value = "/queryPolicyList2/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgentOrcAdjustPolicyVo>> queryPolicyList2(@RequestBody Criterion criterion) {
        var pageResult = agentOrcAdjustService.queryPolicyList(criterion);
        return ResponseEntity.ok(pageResult);
    }
    /**
     * 業績調整新增
     *
     * @param request 請求對象
     * @return
     */
    @Operation(summary = "業績調整新增")
    @Description("業績調整新增")
    @PostMapping(value = "/createAgentOrcAdjust/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentOrcAdjustDetailVO> createAgentOrcAdjust(@RequestBody AgentOrcAdjustDetailDTO request) {
        var resultDto = this.agentOrcAdjustService.createAgentOrcAdjust(request);
        var result = AgentOrcAdjustDetailMapper.INSTANCE.convert(resultDto);
        //測試111
        //測試2
        //測試3
        //測試4

        return ResponseEntity.ok(result);
    }

    /**
     * 業績調整更新
     *
     * @param request 請求對象
     * @return
     */
    @Operation(summary = "業績調整更新")
    @Description("業績調整更新")
    @PostMapping(value = "/updateAgentOrcAdjust/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentOrcAdjustDetailVO> updateAgentOrcAdjust(@RequestBody AgentOrcAdjustDetailDTO request) {
        var resultDto = this.agentOrcAdjustService.updateAgentOrcAdjust(request);
        var result = AgentOrcAdjustDetailMapper.INSTANCE.convert(resultDto);
        return ResponseEntity.ok(result);
    }

    /**
     * 取回
     *
     * @param keys 請求對象
     * @return
     */
    @Operation(summary = "取回")
    @Description("取回")
    @PostMapping(value = "/backAgentOrcAdjust/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity backAgentOrcAdjust(@RequestBody List<String> keys) {
        agentOrcAdjustService.backAgentOrcAdjust(keys);
        return ResponseEntity.ok().build();
    }

    /**
     * 審核
     *
     * @param request 請求對象
     * @return
     */
    @Operation(summary = "審核")
    @Description("審核")
    @PostMapping(value = "/reviewAgentOrcAdjust/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reviewAgentOrcAdjust(@RequestBody ReviewDTO request) {
        agentOrcAdjustService.reviewAgentOrcAdjust(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 業績調整详情
     *
     * @param key 請求對象
     * @return
     */
    @Operation(summary = "業績調整详情")
    @Description("業績調整详情")
    @Parameter(name = "key", description = "主鍵Key", required = true)
    @PostMapping(value = "/getAgentOrcAdjust/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentOrcAdjustDetailVO> getAgentOrcAdjust(@RequestParam String key) {
        return ResponseEntity.ok(this.agentOrcAdjustService.getAgentOrcAdjustDetail(key));
    }

    /**
     * 業績調整刪除
     *
     * @param keys 請求對象
     * @return
     */
    @Operation(summary = "業績調整刪除")
    @Description("業績調整刪除")
    @PostMapping(value = "/deleteAgentOrcAdjust/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAgentOrcAdjust(@RequestBody List<String> keys) {
        agentOrcAdjustService.deleteAgentOrcAdjust(keys);
        return ResponseEntity.ok().build();
    }


    /**
     * 試算
     *
     * @param keys key
     */
    @Operation(summary = "試算")
    @Description("試算")
    @PostMapping(value = "/tryCalc/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentOrcAdjustTryCalcVO> tryCalc(@RequestBody List<String> keys) {
        var result = AgentOrcAdjustTryCalcMapper.INSTANCE.convert(new AgentOrcAdjustTryCalcDTO());
        return ResponseEntity.ok(result);
    }

    /**
     * 業績調整批量導入
     */
    @Operation(summary = "業績調整批量導入")
    @Description("業績調整批量導入")
    @PostMapping(value = "/importAgentOrcAdjust/v1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void importAgentOrcAdjust(@RequestPart(name = "file") FilePart file, ServerHttpResponse response) {

        File tempFile = FileUtils.toFile(file);
        try {
            List<ExcelUtils.ExcelRow<AgentOrcAdjustDetailDTO>> data = agentOrcAdjustService.importAgentOrcAdjustDetail(ExcelUtils.read(tempFile, AgentOrcAdjustDetailDTO.class));
            // 生成导入报告
            FileUtils.downloadFile(response, tempFile, file.filename(), data);

        } catch (Exception e) {
            LOGGER.error("業績調整批量導入 {}", e.getMessage(), e);
            throw new BusinessValidationException(e);
        } finally {
            //刪除臨時文件
            tempFile.delete();
        }
    }

}


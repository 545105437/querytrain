package com.train.service;

import com.train.dto.SearchLogHistoryDTO;
import com.train.model.SearchLogHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author wal
 * @date 2017/12/28
 */
public interface SearchLogHistoryService {

    /**
     * 按照时间查询查询日志信息
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    Page<SearchLogHistoryDTO> findSearchLogHistoriesByVisitTimeBetween(Date startTime, Date endTime, Pageable pageable);

    /**
     * 按照时间查询查询日志信息(endTime不存在的情况)  大于开始时间的所有数据
     * @param startTime
     * @param pageable
     * @return
     */
    Page<SearchLogHistoryDTO> findSearchLogHistoriesByVisitTimeGreaterThanEqual(Date startTime, Pageable pageable);

    /**
     * 按照时间查询查询日志信息(startTime不存在的情况)  小于结束时间的所有数据
     * @param endTime
     * @param pageable
     * @return
     */
    Page<SearchLogHistoryDTO> findSearchLogHistoriesByVisitTimeLessThanEqual(Date endTime, Pageable pageable);

    /**
     * 查询所有记录
     * @param pageable
     * @return
     */
    Page<SearchLogHistoryDTO> findAll(Pageable pageable);

    SearchLogHistory save(SearchLogHistory searchLogHistory);
}

package com.train.Repository;

import com.train.model.SearchLogHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @author wal
 * @date 2017/12/28
 */
public interface SearchLogHistoryRepository extends JpaRepository<SearchLogHistory,Integer>{

    /**
     * 按照时间查询查询日志信息(startTime和endTime都有的情况)
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    Page<SearchLogHistory> findSearchLogHistoriesByVisitTimeBetween(Date startTime, Date endTime, Pageable pageable);

    /**
     * 按照时间查询查询日志信息(endTime不存在的情况)  大于开始时间的所有数据
     * @param startTime
     * @param pageable
     * @return
     */
    Page<SearchLogHistory> findSearchLogHistoriesByVisitTimeGreaterThanEqual(Date startTime, Pageable pageable);

    /**
     * 按照时间查询查询日志信息(startTime不存在的情况)  小于结束时间的所有数据
     * @param endTime
     * @param pageable
     * @return
     */
    Page<SearchLogHistory> findSearchLogHistoriesByVisitTimeLessThanEqual(Date endTime, Pageable pageable);


}

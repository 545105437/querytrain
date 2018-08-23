package com.train.service.impl;

import com.train.Repository.SearchLogHistoryRepository;
import com.train.coverter.SearchLogHistory2SearchLogHistoryDTOConverter;
import com.train.dto.SearchLogHistoryDTO;
import com.train.model.SearchLogHistory;
import com.train.service.SearchLogHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wal
 * @date 2017/12/28
 */
@Service
@Slf4j
public class SearchLogHistoryServiceImp implements SearchLogHistoryService{

    @Autowired
    private SearchLogHistoryRepository searchLogHistoryRepository;

    @Override
    public Page<SearchLogHistoryDTO> findSearchLogHistoriesByVisitTimeBetween(Date startTime, Date endTime, Pageable pageable) {
        Page<SearchLogHistory> searchLogHistoryPage = searchLogHistoryRepository.findSearchLogHistoriesByVisitTimeBetween(startTime,endTime,pageable);

        List<SearchLogHistoryDTO> searchLogHistoryDTOList = SearchLogHistory2SearchLogHistoryDTOConverter.convert(searchLogHistoryPage.getContent());

        return new PageImpl<SearchLogHistoryDTO>(searchLogHistoryDTOList, pageable, searchLogHistoryPage.getTotalElements());
    }

    @Override
    public Page<SearchLogHistoryDTO> findSearchLogHistoriesByVisitTimeGreaterThanEqual(Date startTime, Pageable pageable) {
        Page<SearchLogHistory> searchLogHistoryPage = searchLogHistoryRepository.findSearchLogHistoriesByVisitTimeGreaterThanEqual(startTime,pageable);

        List<SearchLogHistoryDTO> searchLogHistoryDTOList = SearchLogHistory2SearchLogHistoryDTOConverter.convert(searchLogHistoryPage.getContent());

        return new PageImpl<SearchLogHistoryDTO>(searchLogHistoryDTOList, pageable, searchLogHistoryPage.getTotalElements());
    }

    @Override
    public Page<SearchLogHistoryDTO> findSearchLogHistoriesByVisitTimeLessThanEqual(Date endTime, Pageable pageable) {
        Page<SearchLogHistory> searchLogHistoryPage = searchLogHistoryRepository.findSearchLogHistoriesByVisitTimeLessThanEqual(endTime,pageable);

        List<SearchLogHistoryDTO> searchLogHistoryDTOList = SearchLogHistory2SearchLogHistoryDTOConverter.convert(searchLogHistoryPage.getContent());

        return new PageImpl<SearchLogHistoryDTO>(searchLogHistoryDTOList, pageable, searchLogHistoryPage.getTotalElements());
    }

    @Override
    public Page<SearchLogHistoryDTO> findAll(Pageable pageable) {
        Page<SearchLogHistory> searchLogHistoryPage = searchLogHistoryRepository.findAll(pageable);

        List<SearchLogHistoryDTO> searchLogHistoryDTOList = SearchLogHistory2SearchLogHistoryDTOConverter.convert(searchLogHistoryPage.getContent());

        return new PageImpl<SearchLogHistoryDTO>(searchLogHistoryDTOList, pageable, searchLogHistoryPage.getTotalElements());
    }

    @Override
    public SearchLogHistory save(SearchLogHistory searchLogHistory) {
        return searchLogHistoryRepository.save(searchLogHistory);
    }
}

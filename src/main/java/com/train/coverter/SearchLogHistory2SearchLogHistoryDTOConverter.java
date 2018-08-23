package com.train.coverter;

import com.train.dto.SearchLogHistoryDTO;
import com.train.model.SearchLogHistory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wal
 * @date 2018/3/8
 */
public class SearchLogHistory2SearchLogHistoryDTOConverter {

    public static SearchLogHistoryDTO convert(SearchLogHistory searchLogHistory){
        SearchLogHistoryDTO searchLogHistoryDTO = new SearchLogHistoryDTO();
        BeanUtils.copyProperties(searchLogHistory, searchLogHistoryDTO);
        return searchLogHistoryDTO;
    }

    public static List<SearchLogHistoryDTO> convert(List<SearchLogHistory> searchLogHistoryList){
        return searchLogHistoryList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}

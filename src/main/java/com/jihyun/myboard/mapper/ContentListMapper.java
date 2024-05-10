package com.jihyun.myboard.mapper;
import com.jihyun.myboard.dto.ContentDTO;
import com.jihyun.myboard.dto.ContentListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentListMapper {

    public List<ContentListDTO> contentBookListView();

}

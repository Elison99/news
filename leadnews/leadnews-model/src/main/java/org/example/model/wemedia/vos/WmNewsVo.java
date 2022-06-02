package org.example.model.wemedia.vos;

import lombok.Data;
import org.example.model.wemedia.pojos.WmNews;

@Data
public class WmNewsVo  extends WmNews {
    /**
     * 作者名称
     */
    private String authorName;
}
package site.ncov.www.ncov.common.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_dic")
@ApiModel(value="DicVo对象", description="")
public class DicVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dic_id", type = IdType.AUTO)
    private Integer dicId;

    private String dicFiled;

    private String dicName;

    private Integer dicValue;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}

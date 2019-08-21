package com.fh.shop.backend.response;

import java.io.Serializable;
import java.util.List;

public class AreaResponse implements Serializable {

    /**
     * code : 200
     * message : success
     * data : [{"id":4,"areaName":"河南省","pId":1},{"id":5,"areaName":"北京","pId":1},{"id":9,"areaName":"111","pId":1},{"id":10,"areaName":"222","pId":1}]
     */

    private int code;
    private String message;
    private List<AreaVO> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AreaVO> getData() {
        return data;
    }

    public void setData(List<AreaVO> data) {
        this.data = data;
    }

    public static class AreaVO {
        /**
         * id : 4
         * areaName : 河南省
         * pId : 1
         */

        private int id;
        private String areaName;
        private int pId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public int getPId() {
            return pId;
        }

        public void setPId(int pId) {
            this.pId = pId;
        }
    }
}

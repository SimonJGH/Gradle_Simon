package com.yds.gradle_simon.entity;

import java.util.List;

/**
 * @author YDS
 * @date 2021/4/13
 * @discribe
 */
@SuppressWarnings("all")
public class LoginBean {

    /**
     * code : 200
     * msg : 登录成功
     * data : {"user_id":28416,"role_id":"3","username":"18210431042","truename":"于德水","mobile":"18210431042","status":1,"head_img":"https://pic.faw-hongqiacademy.com/Uploads/Website/161519654656222.jpg?x-oss-process=style/user","point":58,"vod_time":60,"gender":"男","post_list":[{"postid":205,"is_rz":0,"name":"运维测试员"}],"orgids":"1764","org_list":{"name":"运维组","description":"运维组"},"orgCodes":"0","givecredit":20,"token":"F9GJYZ4M96O004C8S4OK0COG8W8OSSK","GlobalNodisturb":0}
     */

    private int code;
    private String msg;
    /**
     * user_id : 28416
     * role_id : 3
     * username : 18210431042
     * truename : 于德水
     * mobile : 18210431042
     * status : 1
     * head_img : https://pic.faw-hongqiacademy.com/Uploads/Website/161519654656222.jpg?x-oss-process=style/user
     * point : 58
     * vod_time : 60
     * gender : 男
     * post_list : [{"postid":205,"is_rz":0,"name":"运维测试员"}]
     * orgids : 1764
     * org_list : {"name":"运维组","description":"运维组"}
     * orgCodes : 0
     * givecredit : 20
     * token : F9GJYZ4M96O004C8S4OK0COG8W8OSSK
     * GlobalNodisturb : 0
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int user_id;
        private String role_id;
        private String username;
        private String truename;
        private String mobile;
        private int status;
        private String head_img;
        private int point;
        private int vod_time;
        private String gender;
        private String orgids;
        /**
         * name : 运维组
         * description : 运维组
         */

        private OrgListBean org_list;
        private String orgCodes;
        private int givecredit;
        private String token;
        private int GlobalNodisturb;
        /**
         * postid : 205
         * is_rz : 0
         * name : 运维测试员
         */

        private List<PostListBean> post_list;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getVod_time() {
            return vod_time;
        }

        public void setVod_time(int vod_time) {
            this.vod_time = vod_time;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getOrgids() {
            return orgids;
        }

        public void setOrgids(String orgids) {
            this.orgids = orgids;
        }

        public OrgListBean getOrg_list() {
            return org_list;
        }

        public void setOrg_list(OrgListBean org_list) {
            this.org_list = org_list;
        }

        public String getOrgCodes() {
            return orgCodes;
        }

        public void setOrgCodes(String orgCodes) {
            this.orgCodes = orgCodes;
        }

        public int getGivecredit() {
            return givecredit;
        }

        public void setGivecredit(int givecredit) {
            this.givecredit = givecredit;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getGlobalNodisturb() {
            return GlobalNodisturb;
        }

        public void setGlobalNodisturb(int GlobalNodisturb) {
            this.GlobalNodisturb = GlobalNodisturb;
        }

        public List<PostListBean> getPost_list() {
            return post_list;
        }

        public void setPost_list(List<PostListBean> post_list) {
            this.post_list = post_list;
        }

        public static class OrgListBean {
            private String name;
            private String description;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class PostListBean {
            private int postid;
            private int is_rz;
            private String name;

            public int getPostid() {
                return postid;
            }

            public void setPostid(int postid) {
                this.postid = postid;
            }

            public int getIs_rz() {
                return is_rz;
            }

            public void setIs_rz(int is_rz) {
                this.is_rz = is_rz;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

new Vue({
    el: "#app",
    data() {
        return {
            dateOfBirth: '',
            portrait: [],
            formLabelWidth: '',
            dialogFormVisible: false,
            sexT: [{
                value: '0',
                label: '男'
            }, {
                value: '1',
                label: '女'
            }, {
                value: '2',
                label: '保密'
            }],
            defaultSex: '',
            height: {
                proH: ''
            },
            URL: {
                imgUrl: 'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'
            },
            acceptFile: {
                img: 'image/gif,image/vnd.dwg,image/vnd.dxf,image/jp2,image/jpeg,image/png,image/tiff',
                imgType: '',
            },
            USER: {
                id: '',
                portrait: '',
                nicknames: '',
                sex: '',
                age: ''
            },
        };
    },
    created() {
        this.StartLoading()
    },
    mounted() {
        this.whpor();
    },
    methods: {
        submit() {

        },
        exitSystem() {
            axios({
                mounted: "POST",
                url: "exitSysServlet"
            }).then(function (resp) {
                if (resp.data !== "0") {
                    window.location = 'login.html'
                }
            })
        },
        skip() {
            window.location = 'login.html'
        },
        NotamendData() {
            this.dialogFormVisible = false;
            this.StartLoading();
        },
        amendData() {
            let JsonData = {
                "id": this.USER.id,
                "portrait": this.USER.portrait,
                "Nicknames": this.USER.nicknames,
                "sex": this.USER.sex,
                "age": this.USER.age,
            };
            let _this = this;
            axios({
                method: "POST",
                url: "amendServlet",
                data: JsonData
            }).then(function (resp) {
                if (resp.data === 'yes') {
                    _this.dialogFormVisible = false;
                    _this.StartLoading();
                } else {
                    _this.hint1();
                }
            });
        },
        hint1() {
            this.$message.error('抱歉,修改失败,可能信息有误');
        },
        whpor() {
            let _this = this;
            this.timer = setTimeout(function () {
                _this.height.porH = _this.$refs.porW.offsetWidth;
                document.getElementById("proHH").style.height = _this.height.porH + "px";
            }, 1000)
        },
        StartLoading() {
            let _this = this;
            axios({
                mounted: "POST",
                url: "gainUserDataServlet"
            }).then(function (resp) {
                if (resp.data === "NULL") {
                    window.location.reload();
                    _this.skip();
                } else {
                    let User = resp.data;
                    _this.USER.id = User.id;
                    _this.USER.portrait = User.portrait;
                    _this.USER.nicknames = User.nicknames;
                    _this.USER.sex = User.sex;
                    _this.defaultSex = _this.sexT[_this.USER.sex].label
                    _this.USER.age = User.age;
                }
            });
        },
        selectSex(num) {
            this.USER.sex = num;
        },
        UserImg(file, fileList) {
            let formData = new FormData();
            formData.append('file', file.raw);
            let _this = this;
            axios({
                method: "POST",
                url: 'uploadServlet',
                data: formData
            }).then(function (resp) {
                if (resp.data === "NO") {
                    _this.$message({
                        showClose: true,
                        message: "上传头像时出现错误" + resp.data,
                        type: 'error',
                    })
                } else {
                    _this.USER.portrait = resp.data;
                }
            })
        },
        uploadImg(file) {
            let ImgType = ['image/gif', 'image/vnd.dwg', 'image/vnd.dxf', 'image/jp2', 'image/jpeg', 'image/png', 'image/tiff'];
            if (ImgType.indexOf(this.imgType) === -1) {
                this.$message({
                    showClose: true,
                    message: "只能使用图片,请重新上传！",
                    type: 'error',
                })
                return false;
            }
            return true;
        },
        handleAvatarSuccess(res, file) {
            this.imgType = URL.createObjectURL(file.raw);
        },
    }
});
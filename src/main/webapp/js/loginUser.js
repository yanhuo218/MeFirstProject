let Verify_user = false;
let Verify_pass = false;
new Vue({
    el: "#app",
    data() {
        return {
            account: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    {required: true, message: '请输入邮箱地址', trigger: 'blur'},
                    {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']},
                    {
                        trigger: 'blur', validator: (rule, value, callback) => {
                            if (value !== "") {
                                axios({
                                    method: "POST",
                                    url: "loginServlet",
                                    data: "username=" + value
                                }).then(function (resp) {
                                    if (resp.data === 2) {
                                        callback(new Error("账号不存在"));
                                        Verify_user = false;
                                    } else if (resp.data === 1) {
                                        Verify_user = true;
                                    }
                                });
                            }

                        }
                    }
                ],
                password: [
                    {required: true, message: '密码不能为空', transform: value => value, trigger: 'blur'},
                    {
                        type: 'string', message: '请输入不包含空格的字符', trigger: 'blur',
                        transform(value) {
                            if (value && value.indexOf(' ') === -1) {
                                return value
                            } else {
                                return false
                            }
                        }
                    },
                    {
                        trigger: 'blur', validator: (rule, value, callback) => {
                            const passwordReg = /^\w{8,64}$/;
                            if (!passwordReg.test(value)) {
                                Verify_pass = false;
                                callback(new Error('密码必须由数字、字母组合,请输入8-64位'))
                            } else {
                                Verify_pass = true;
                                callback();
                            }
                        }
                    }
                ]
            }
        };
    },

    methods: {
        submitForm() {
            const JSON_USER = {
                "username": this.account.username,
                "password": this.account.password
            }
            let th = this;
            if (Verify_user === true && Verify_pass === true) {
                axios({
                    method: "POST",
                    url: "loginTwoServlet",
                    data: JSON_USER
                }).then(function (resp) {
                    if (resp.data === "YES") {
                        th.open1();
                    } else {
                        th.open2();
                    }
                })
            }
        },
        open1() {
            let _this = this;
            this.$message({
                message: '登录成功,1秒后自动跳转',
                type: 'success'
            });
            setTimeout(function () {
                window.location = 'index.html'
            }, 1000);
        },
        open2() {
            this.$message.error('抱歉，登录失败，密码错误,或账号以封禁');
        },
        reset(formName) {//重置
            this.$refs[formName].resetFields();
        }
    }
})
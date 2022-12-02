let Verify_user = false;
let Verify_pass = false;
let Verify_two = false;
new Vue({
    el: "#app",
    data: function () {
        return {
            account: {
                username: '',
                password: '',
                checkPass: ''
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
                                        Verify_user = true;
                                    } else if (resp.data === 1) {
                                        callback(new Error("账号已存在"));
                                        Verify_user = false;
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
                ],
                checkPass: [
                    {required: true, message: '密码不能为空', trigger: 'blur'},
                    {
                        trigger: 'blur', validator: (rule, value, callback) => {
                            if (value !== this.account.password) {
                                callback(new Error('请输入一致的密码'))
                                Verify_two = false;
                            } else {
                                Verify_two = true;
                                callback();
                            }
                        }
                    }
                ]
            }
        };
    },
    methods: {
        reset(formName) {
            this.$refs[formName].resetFields();
        },
        submitForm() {
            let JSON_USER = {
                "username": this.account.username,
                "password": this.account.password,
            }
            let th = this;
            if (Verify_user === true && Verify_pass === true && Verify_two === true) {
                Verify_user = false;
                axios({
                    method: "POST",
                    url: "newUserServlet",
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
            this.$message({
                message: '感谢注册!您可以回到登录页登录了,或者1秒后自动跳转',
                type: 'success'
            });
            setTimeout(function () {
                window.location = "login.html";
            }, 1000);
        },
        open2() {
            this.$message.error('抱歉，注册失败，账号信息已存在');
        }
    }
})
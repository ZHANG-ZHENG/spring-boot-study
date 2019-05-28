<template>
  <el-form :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox class="login_remember" v-model="checked"
                 label-position="left">记住密码</el-checkbox>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%" @click="submitClick">登录</el-button>{{loginResponse}}
      <el-button type="primary" style="width: 100%" @click="logoutClick">退出</el-button>
      <el-button type="primary" style="width: 100%" @click="testClick">test</el-button>{{testResponse}}
      <el-button type="primary" style="width: 100%" @click="test2Click">test2</el-button>{{test2Response}}
      <el-button type="primary" style="width: 100%" @click="downloadClick">download</el-button>{{downloadResponse}}
    </el-form-item>
  </el-form>
</template>
<script>
  export default{
    data(){
      return {
        rules: {
          account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}]
        },
        checked: true,
        loginForm: {
          username: 'admin',
          password: '123'
        },
        loginResponse:'-',
        testResponse:'-',        
        test2Response:'-',
        downloadResponse:'-',
        loading: false
      }
    },
    methods: {
      submitClick: function () {
        var _this = this;
        //this.loading = true;
        let uploadData = new window.FormData()
        uploadData.append('username', this.loginForm.username)
        uploadData.append('password', this.loginForm.password)

        this.$http.post('/login', uploadData).then(resp=> {
          //_this.loading = false;
          console.log(resp);
          _this.loginResponse = resp.bodyText;
          // if (resp && resp.status == 200) {
            
          // }
        });
      },
      logoutClick: function () {
        var _this = this;

        this.$http.post('/logout').then(resp=> {
          console.log(resp);
        });
      },      
      testClick: function () {
        var _this = this;
        //this.loading = true;
        this.$http.post('/test').then(resp=> {
          //_this.loading = false;
          console.log(resp);
          _this.testResponse = resp.bodyText;
          // if (resp && resp.status == 200) {
            
          // }
        });
      },
      test2Click: function () {
        var _this = this;
        //this.loading = true;
        this.$http.post('/test2').then(resp=> {
          //_this.loading = false;
          console.log(resp);
          _this.test2Response = resp.bodyText;
          // if (resp && resp.status == 200) {
            
          // }
        });
      },  
      downloadClick: function () {
        var _this = this;
        // this.$http.post('/download').then(resp=> {
        //   console.log(resp);
        //   _this.downloadResponse = resp.bodyText;
        // });
        this.$http.post('/download').then(function(res){
          _this.downloadResponse = res.bodyText;
                            
        },function(res){
          _this.downloadResponse = res.bodyText;
        });       
      },                
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>

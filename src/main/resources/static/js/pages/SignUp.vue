<template>
    signup
    <form action="/registration" method="post" class="user">
        <div class="form-group">

            <input type="text" class="form-control form-control-user"
                   id="exampleInputEmail" name="username" aria-describedby="emailHelp"
                   placeholder="Логин">
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control form-control-user"
                   id="exampleInputPassword" placeholder="Пароль">
        </div>
        <div class="form-group">
            <div class="custom-control custom-checkbox small">
                <input type="checkbox" class="custom-control-input" id="customCheck">
                <label class="custom-control-label" for="customCheck">Запомнить меня</label>
            </div>
        </div>


        <input type="submit" value="Отправить" class="btn btn-primary btn-user btn-block"/>
        <hr>
    </form>
    <div class="text-center">
        <a class="small" href="/registration">Создать аккаунт</a>
    </div>
</template>

<script>
    export default {
        name: 'SignUp',
    }
</script>

<style>

</style>
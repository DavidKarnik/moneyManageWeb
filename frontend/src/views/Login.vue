<template>
    <div>
        <h2>Login</h2>
        <form @submit.prevent="login">
            <label for="username">Username:</label>
            <input v-model="username" type="text" id="username" required />

            <label for="password">Password:</label>
            <input v-model="password" type="password" id="password" required />

            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            username: "",
            password: "",
        };
    },
    methods: {
        login() {
            // Zde můžete implementovat logiku přihlášení
            console.log("Login with:", this.username, this.password);

            // Po úspěšném přihlášení můžete přesměrovat na hlavní stránku
            this.$router.push("/home");
        },
    },
};
</script>

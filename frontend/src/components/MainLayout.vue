<template>
    <div>
        <header>
            <h1>My Vue App</h1>
            <router-link to="/">Home</router-link>
            <router-link to="/login">Login</router-link>
        </header>
        <main>
            <router-view></router-view>
        </main>
    </div>
</template>

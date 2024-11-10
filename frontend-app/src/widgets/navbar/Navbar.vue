<script setup lang="ts">
import { AppRoutes } from '@/app/providers'
</script>

<template>
	<div class="navbar">
		<router-link :to="AppRoutes.getHome()">Home</router-link>
		<router-link :to="AppRoutes.getBooks()">Books</router-link>
	</div>
</template>

<style scoped>
.navbar
{
	display: flex;
	flex-direction: row;
	gap: 20px;
}
</style>

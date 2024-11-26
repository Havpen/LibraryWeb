<script setup lang="ts">
import Navbar from '@/widgets/navbar/Navbar.vue'
</script>

<template>
	<div class="app-wrapper">
		<Navbar/>
		<router-view/>
	</div>
</template>

<style>
*
{
	margin: 0;
	padding: 0;
	font-weight: normal;
}

.app-wrapper
{
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	font-family: Tahoma, Arial, sans-serif;
}
</style>

<script setup lang="ts">
import Navbar from '@/widgets/navbar/Navbar.vue'
</script>

<template>
	<div class="app-wrapper">
		<Navbar/>
		<router-view/>
	</div>
</template>

<style scoped>
.app-wrapper
{
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
}
</style>

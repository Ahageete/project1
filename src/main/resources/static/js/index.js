const App = {
	data() {
		return {
			menuList: []
		}
	},
	mounted: function() {
		this.$nextTick(function() {
			this.getData();
		})
	},
	methods: {
		getData() {
			var vueMenuList = this.menuList;
			$.get('https://localhost/system/menu/byUser', function(data) {
				$(data.data).each(function(i, k) {
					vueMenuList.push(k);
				});
			});
		},
		goToUrl(url) {
			alert("/pages" + url + ".html");
		},
		testPage() {
			$.get('https://localhost/system/menu/all1', function(data) {
				console.log(data);
			});
		}

	}
}
Vue.createApp(App).mount('#app')

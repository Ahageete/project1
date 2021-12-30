const App = {
	data() {
		return {
			counter: 0,
			condition: true,
			edit_add: false,
			menuObjList: [],
			menuId: "",
			menuName: "",
			menuUrl: "",
			pageNo: 1,
			pageSize: 10,
			windowOpen: false
		}
	},
	mounted: function() {
		this.$nextTick(function() {
			this.getData();
		})
	},
	methods: {
		getData() {
			var vueMenuObjList = this.menuObjList;
			$.get('https://localhost/system/menu/byUser?pageSize=' + this.pageSize + '&pageNo=' + this.pageNo,
				function(data) {
					$(data.data).each(function(i, k) {
						vueMenuObjList.push(k);
					});
				});
		},
		addOne() {
			if (this.windowOpen) {
				var flag = confirm("确定取消当前操作并执行添加?");
				if (flag) {
					this.menuId = null;
					this.menuName = null;
					this.menuUrl = null;
				}
			} else {
				this.windowOpen = true;
				this.menuId = null;
				this.menuName = null;
				this.menuUrl = null;
				this.edit_add = true;
			}
		},
		delOne(id) {
			if (this.windowOpen) {
				var flag = confirm("确定取消当前操作并执行编辑?");
				if (flag) {
					alert("delete:" + id);
					this.menuId = null;
					this.menuName = null;
					this.menuUrl = null;
					this.windowOpen = false;
					this.edit_add = false;
				}
			}
		},
		cancelOne() {
			var flag = confirm("确定取消?");
			if (flag) {
				this.menuId = null;
				this.menuName = null;
				this.menuUrl = null;
				this.edit_add = false;
				this.windowOpen = false;
			} else {

			}
		},
		editOne(obj) {
			console.log(obj);
			if (this.windowOpen) {
				var flag = confirm("确定取消当前操作并执行编辑?");
				if (flag) {
					this.edit_add = true;
					this.windowOpen = true;
					this.menuId = obj.menuId;
					this.menuName = obj.menuName;
					this.menuUrl = obj.menuUrl;
				}
			} else {
				this.edit_add = true;
				this.windowOpen = true;
				this.menuId = obj.menuId;
				this.menuName = obj.menuName;
				this.menuUrl = obj.menuUrl;
			}
		},
		saveOne() {
			var flag = confirm("确定保存?");
			if (flag) {
				alert("save data:" + this.menuId + "," + this.menuName + "," + this.menuUrl);
				this.edit_add = false;
				this.windowOpen = false;
			} else {

			}
		}
	}
}
Vue.createApp(App).mount('#app')

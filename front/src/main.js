import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
//import axios from '@/plugins/axios'


// Components
import mainNavbar from '@/components/MainNavbar'
import mainFooter from '@/components/MainFooter'
import mainSidebar from '@/components/MainSidebar'
import tagButton from '@/components/TagButton'
createApp(App)
    .use(router)
    //.use(axios)
    //.config.globalProperties(title, 'Buffer Overflow')
    .component('mainNavbar', mainNavbar)
    .component('mainFooter', mainFooter)
    .component('mainSidebar', mainSidebar)
    .component('tagButton', tagButton)
    .mount('#app')

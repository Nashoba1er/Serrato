import WelcomeBlog from "@/views/WelcomeBlog.vue";
import ArticleDetails from "@/views/ArticleDetails.vue";
import {createRouter} from "vue-router";
import {createWebHistory} from "vue-router";
import PageAccueil from "@/views/PageAccueil.vue";
import AboutPage from "@/views/AboutPage.vue";
import LoginPage from "@/views/LoginPage.vue";
import CreateAccountPage from "@/views/CreateAccountPage.vue";
import DataPage from "@/views/DataPage.vue";
import TestPage from "@/views/TestPage.vue";




const routes = [
    {
        path:'/blog', //localhost
        name:"Blog",
        component: WelcomeBlog, //page accueil blog
    },
    {
        path:'/blog/article/:id', //id qui change pour les différents articles d'où le :
        name:"article",
        component: ArticleDetails,
    },
    {
        path:'/', //localhost
        name:"Accueil",
        component: PageAccueil,
    },
    {
        path:'/about',
        name:"About",
        component: AboutPage,
    },
    {
        path:'/login',
        name:"Login",
        component: LoginPage,
    },
    {
        path:'/create-account',
        name:"Account",
        component: CreateAccountPage,
    },
    {
        path:'/data',
        name:"data",
        component: DataPage,
    },
    {
        path:'/test',
        name:"test",
        component: TestPage,
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
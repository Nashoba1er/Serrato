import WelcomeBlog from "@/views/WelcomeBlog.vue";
import ArticleDetails from "@/views/ArticleDetails.vue";
import {createRouter} from "vue-router";
import {createWebHistory} from "vue-router";
import PageAccueil from "@/views/PageAccueil.vue";
import AboutPage from "@/views/AboutPage.vue";
import LoginPage from "@/views/LoginPage.vue";
import CreateAccountPage from "@/views/CreateAccountPage.vue";
import VosSerresPage from "@/views/VosSerresPage.vue";
import GreenhouseDetails from "@/views/GreenhouseDetails.vue";
import CreateSerrePage from "@/views/CreateSerrePage.vue";
import {authGuard} from "@/_helpers/auth-guard";
import CreateHeatersPage from "@/views/CreateHeatersPage.vue";




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
        path:'/vosserres',
        name:"Serres",
        component: VosSerresPage, beforeEnter: authGuard
    },
    {
        path:'/vosserres/:id', //id qui change pour les différents articles d'où le :
        name:"serre",
        component: GreenhouseDetails,
    },
    {
        path:'/vosserres/createserre', //id qui change pour les différents articles d'où le :
        name:"créer serre",
        component: CreateSerrePage,
    },
    {
        path:'/vosserres/createheater', //id qui change pour les différents articles d'où le :
        name:"créer heater",
        component: CreateHeatersPage,
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
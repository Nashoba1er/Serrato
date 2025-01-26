//import router from "@/router/router";


export function authGuard(to, from, next){
    let token = localStorage.getItem('token')
    console.log(token)
    if (!token) {
        // Redirige vers /login avec une requête
        next({ path: '/login', query: { reason: 'auth-required' } });
    } else {
        next(); // Autorise la navigation
    }
}
<template>
    <div class="wrapper">
        <nav v-if="profile" id="sidebar" class="sidebar js-sidebar">
            <div class="sidebar-content js-simplebar">
                <a class="sidebar-brand" href="index.html">
                    <span class="align-middle">Cometa</span>
                </a>

                <ul class="sidebar-nav">
                    <li class="sidebar-header">
                        Pages
                    </li>

                    <li class="sidebar-item active">
                        <a class="sidebar-link" href="index.html">
                            <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="pages-profile.html">
                            <i class="align-middle" data-feather="user"></i> <span class="align-middle">Profile</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="pages-sign-in.html">
                            <i class="align-middle" data-feather="log-in"></i> <span class="align-middle">Sign In</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="pages-sign-up.html">
                            <i class="align-middle" data-feather="user-plus"></i> <span class="align-middle">Sign Up</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="pages-blank.html">
                            <i class="align-middle" data-feather="book"></i> <span class="align-middle">Blank</span>
                        </a>
                    </li>

                    <li class="sidebar-header">
                        Tools & Components
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="ui-buttons.html">
                            <i class="align-middle" data-feather="square"></i> <span class="align-middle">Buttons</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="ui-forms.html">
                            <i class="align-middle" data-feather="check-square"></i> <span class="align-middle">Forms</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="ui-cards.html">
                            <i class="align-middle" data-feather="grid"></i> <span class="align-middle">Cards</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="ui-typography.html">
                            <i class="align-middle" data-feather="align-left"></i> <span class="align-middle">Typography</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="icons-feather.html">
                            <i class="align-middle" data-feather="coffee"></i> <span class="align-middle">Icons</span>
                        </a>
                    </li>

                    <li class="sidebar-header">
                        Plugins & Addons
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="charts-chartjs.html">
                            <i class="align-middle" data-feather="bar-chart-2"></i> <span class="align-middle">Charts</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link" href="maps-google.html">
                            <i class="align-middle" data-feather="map"></i> <span class="align-middle">Maps</span>
                        </a>
                    </li>
                </ul>


            </div>
        </nav>

        <div class="main">
            <div class="content">
                <div>
                    Cometa
                    <div v-if="profile">?????????????????????? {{ profile.username }}</div>
                    <div><a href="/logout">??????????</a></div>
                </div>
                <router-view></router-view>
            </div>

        </div>

    </div>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'

    import { addHandler } from 'util/ws'

    export default {
        computed: mapState(['profile']),
        methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE'){
                    switch(data.eventType){
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error('Looks like the event type if unknown')
                    }
                    } else {
                        console.error('Looks like the object type if unknown')
                    }

            })
        }
    }
</script>

<style>

</style>
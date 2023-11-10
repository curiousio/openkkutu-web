/*
 * KKuTu-Web (https://github.com/KKuTuIO/KKuTu-Web)
 * Copyright (C) 2021 KKuTuIO <admin@kkutu.io>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import React, {useEffect, useState} from "react";
import $ from 'jquery';
import Separator from "../../components/Separator";
import '../../../css/in_portal.css';
import {MiddlePanel, UpdateLog} from "./PortalStyle";
import Product from "../../components/Product";
import KakaoAd from "../../components/KakaoAd";
import TopLeftPanel from "./TopLeftPanel";
import TopRightPanel from "./TopRightPanel";
import {BrowserView, MobileView} from "react-device-detect";
import Axios from "axios";
import Blocked from "../../components/blocked/Blocked";

const Portal = () => {
    const [isInitializing, setInitializing] = useState(true);
    const [messages, setMessages] = useState({});
    const [servers, setServers] = useState([]);
    const [maxUserPerServer, setMaxUserPerServer] = useState(400);
    const [blockStatus, setBlockStatus] = useState({});

    useEffect(() => {
        setMessages(Messages);
        setInitializing(false);

        getBlockStatus().then(res => {
            const data = res.data;
            setBlockStatus(data);
        })

        $("#Background").removeAttr('src').addClass("jt-image").css({
            'background-image': "url('https://cdn.kkutu.io/img/kkutu/gamebg.png')",
            'background-size': "200px 200px"
        });
    }, [])

    const onServerListUpdate = (list, max) => {
        setServers(list);
        setMaxUserPerServer(max);
    }

    const onGameStart = () => {
        if ($("#account-info").html() === messages['portal.js.login']) joinServer(0);
        else joinServer(findRecommendServerId());
    }

    const findRecommendServerId = () => {
        for (let i = 0.9; i < 1; i += 0.01) {
            for (let server in servers) {
                if (servers[server] >= i * maxUserPerServer)
                    continue;

                return server;
            }
        }

        return 0;
    }

    const joinServer = (id) => {
        location.href = "/?server=" + id;
    }

    const getBlockStatus = () => {
        return Axios.get('/api/block');
    }

    if (isInitializing) return <h1>Page Initializing...</h1>
    return (
        <>
            {blockStatus.blocked && <Blocked blockInfo={blockStatus}/>}
            <Separator height={5}/>
            <TopLeftPanel onGameStart={onGameStart}/>
            <TopRightPanel joinServer={joinServer} onServerListUpdate={onServerListUpdate}/>

            <MiddlePanel>
                <UpdateLog width="100%" height="400px" src="https://static.kkutu.io/kkutu_bulletin"/>
            </MiddlePanel>
            <Separator height={5}/>

            <BrowserView>
                <Product id="qwShKF" title={Messages['portal.ad.title']} createWithShown={true}>
                    <KakaoAd width={728} height={90} unit="DAN-foUc9eTmMwRJkgLR"/>
                </Product>
                <Separator height={10}/>
            </BrowserView>
            <MobileView>
                <Product id="qwShKF" title={Messages['portal.ad.title']} createWithShown={true}>
                    <KakaoAd width={300} height={250} unit="DAN-gFLCejiyrNaIrKfL"/>
                </Product>
                <Separator height={10}/>
            </MobileView>
        </>
    );
}

export default Portal;

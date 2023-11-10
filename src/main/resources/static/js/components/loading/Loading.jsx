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

import React from "react";
import styled from 'styled-components';
import './index.css';

const Backdrop = styled.div`
  background-color: white;
  position: fixed;
  z-index: 100;
  margin: auto;
  border-radius: 10px;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  width: 350px;
  height: 150px;
  opacity: 0.7;
`

const Loading = () => {
    return (
        <>
            <Backdrop/>
            <div className='loader'>
                <div className='loader--dot'/>
                <div className='loader--dot'/>
                <div className='loader--dot'/>
                <div className='loader--dot'/>
                <div className='loader--dot'/>
                <div className='loader--dot'/>
                <div className='loader--text'/>
            </div>
        </>
    )
}

export default Loading;
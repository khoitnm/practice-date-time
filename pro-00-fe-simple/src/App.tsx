import React, {useEffect, useState} from 'react';
import './App.css';
import dateTimeBeService from "./DateTimeBeService";
import {DateTimeEntity} from "./DateTimeEntity";
import DateTimeResult from "./DateTimeResult";

function App() {
  const [dateTime, setDateTime] = useState<DateTimeResult>();

  useEffect(() => {
    dateTimeBeService.now().then((dateTime: DateTimeResult) => {
      setDateTime(dateTime);
    });
  }, []);

  const onRetrieveDateTime = async () => {
    const dateTime: DateTimeResult = await dateTimeBeService.now();
    setDateTime(dateTime);
  }

  return (
    <div>
      <button onClick={onRetrieveDateTime}>Retrieve date time</button>

      <table>
        <tr>
          <th>Field Name</th>
          <th>Now</th>
          <th>Six Months Later</th>
          <th>Local</th>
          <th>UTC</th>
          <th>DateType</th>
        </tr>



        <tr>
          <td>offsetDateTime</td>
          <td>{dateTime?.now.offsetDateTime}</td>
          <td>{dateTime?.sixMonths.offsetDateTime}</td>
          <td>{dateTime?.now.offsetDateTimeDate.toLocaleString()}</td>
          <td>{dateTime?.now.offsetDateTimeDate.toUTCString()}</td>
          <td>{typeof dateTime?.now.offsetDateTimeDate}</td>
        </tr>

        <tr>
          <td>offsetDateTimeInUTC</td>
          <td>{dateTime?.now.offsetDateTimeInUTC}</td>
          <td>{dateTime?.sixMonths.offsetDateTimeInUTC}</td>
          <td>{dateTime?.now.offsetDateTimeInUTCDate.toLocaleString()}</td>
          <td>{dateTime?.now.offsetDateTimeInUTCDate.toUTCString()}</td>
          <td>{typeof dateTime?.now.offsetDateTimeInUTCDate}</td>
        </tr>

        <tr>
          <td>zonedDateTime</td>
          <td>{dateTime?.now.zonedDateTime}</td>
          <td>{dateTime?.sixMonths.zonedDateTime}</td>
          <td>{dateTime?.now.zonedDateTimeDate.toLocaleString()}</td>
          <td>{dateTime?.now.zonedDateTimeDate.toUTCString()}</td>
          <td>{typeof dateTime?.now.zonedDateTimeDate}</td>
        </tr>

        <tr>
          <td>zonedDateTimeInUTC</td>
          <td>{dateTime?.now.zonedDateTimeInUTC}</td>
          <td>{dateTime?.sixMonths.zonedDateTimeInUTC}</td>
          <td>{dateTime?.now.zonedDateTimeInUTCDate.toLocaleString()}</td>
          <td>{dateTime?.now.zonedDateTimeInUTCDate.toUTCString()}</td>
          <td>{typeof dateTime?.now.zonedDateTimeInUTCDate}</td>
        </tr>

        <tr>
          <td>zonedDateTimeInUTC to OffsetDateTime</td>
          <td>{dateTime?.now.zonedDateTimeInUTCToOffsetDateTime}</td>
          <td>{dateTime?.sixMonths.zonedDateTimeInUTCToOffsetDateTime}</td>
          <td></td>
          <td></td>
          <td></td>
        </tr>

        <tr>
          <td>date</td>
          <td>{dateTime?.now.date}</td>
          <td>{dateTime?.sixMonths.date}</td>
          <td>{dateTime?.now.dateDate.toLocaleString()}</td>
          <td>{dateTime?.now.dateDate.toUTCString()}</td>
          <td>{typeof dateTime?.now.dateDate}</td>
        </tr>

        <tr>
          <td>dateInUTC</td>
          <td>{dateTime?.now.dateInUTC}</td>
          <td>{dateTime?.sixMonths.dateInUTC}</td>
          <td>{dateTime?.now.dateInUTCDate.toLocaleString()}</td>
          <td>{dateTime?.now.dateInUTCDate.toUTCString()}</td>
          <td>{typeof dateTime?.now.dateInUTCDate}</td>
        </tr>

        <tr>
          <td>ID</td>
          <td>{dateTime?.now.id}</td>
          <td></td>
          <td></td>
          <td></td>
          <td>{typeof dateTime?.now.id}</td>
        </tr>
      </table>
    </div>
  );
}

export default App;

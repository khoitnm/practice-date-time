import React, {useEffect, useState} from 'react';
import './App.css';
import dateTimeBeService from "./DateTimeBeService";
import {DateTimeEntity} from "./DateTimeEntity";

function App() {
  const [dateTime, setDateTime] = useState<DateTimeEntity>();

  useEffect(() => {
    dateTimeBeService.now().then((dateTime: DateTimeEntity) => {
      setDateTime(dateTime);
    });
  }, []);

  const onRetrieveDateTime = async () => {
    const dateTime: DateTimeEntity = await dateTimeBeService.now();
    setDateTime(dateTime);
  }

  return (
    <div>
      <button onClick={onRetrieveDateTime}>Retrieve date time</button>

      <table>
        <tr>
          <th>Field Name</th>
          <th>JSON</th>
          <th>Local</th>
          <th>UTC</th>
          <th>DateType</th>
        </tr>

        <tr>
          <td>ID</td>
          <td>{dateTime?.id}</td>
          <td></td>
          <td></td>
          <td>{typeof dateTime?.id}</td>
        </tr>

        <tr>
          <td>offsetDateTime</td>
          <td>{dateTime?.offsetDateTime}</td>
          <td>{dateTime?.offsetDateTimeDate.toLocaleString()}</td>
          <td>{dateTime?.offsetDateTimeDate.toUTCString()}</td>
          <td>{typeof dateTime?.offsetDateTimeDate}</td>
        </tr>

        <tr>
          <td>offsetDateTimeInUTC</td>
          <td>{dateTime?.offsetDateTimeInUTC}</td>
          <td>{dateTime?.offsetDateTimeInUTCDate.toLocaleString()}</td>
          <td>{dateTime?.offsetDateTimeInUTCDate.toUTCString()}</td>
          <td>{typeof dateTime?.offsetDateTimeInUTCDate}</td>
        </tr>

        <tr>
          <td>zonedDateTime</td>
          <td>{dateTime?.zonedDateTime}</td>
          <td>{dateTime?.zonedDateTimeDate.toLocaleString()}</td>
          <td>{dateTime?.zonedDateTimeDate.toUTCString()}</td>
          <td>{typeof dateTime?.zonedDateTimeDate}</td>
        </tr>

        <tr>
          <td>zonedDateTimeInUTC</td>
          <td>{dateTime?.zonedDateTimeInUTC}</td>
          <td>{dateTime?.zonedDateTimeInUTCDate.toLocaleString()}</td>
          <td>{dateTime?.zonedDateTimeInUTCDate.toUTCString()}</td>
          <td>{typeof dateTime?.zonedDateTimeInUTCDate}</td>
        </tr>

        <tr>
          <td>date</td>
          <td>{dateTime?.date}</td>
          <td>{dateTime?.dateDate.toLocaleString()}</td>
          <td>{dateTime?.dateDate.toUTCString()}</td>
          <td>{typeof dateTime?.dateDate}</td>
        </tr>

        <tr>
          <td>dateInUTC</td>
          <td>{dateTime?.dateInUTC}</td>
          <td>{dateTime?.dateInUTCDate.toLocaleString()}</td>
          <td>{dateTime?.dateInUTCDate.toUTCString()}</td>
          <td>{typeof dateTime?.dateInUTCDate}</td>
        </tr>
      </table>
    </div>
  );
}

export default App;

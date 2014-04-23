#!/bin/sh

#  Script to roll the service logs at the end of the day.
#

shopt -s xpg_echo

PATH="/bin:/usr/sbin:/usr/bin:/usr/ucb"
host=`hostname`

# Number of days to keep old files
oldlogs=21
minoldlogs=10

gzipexe="/usr/bin/gzip"

#mailto="andrew.johnston@kroger.com"

cklog=/tmp/cklist.$$

trap "rm -f $cklog; exit 0" 0 1 2 3 15
#trap "exit 0" 0 1 2 3 15

echo "\nService Log Rotation Process started on ${host} at `date`\n" > $cklog

# Generate filenames for tonight's log file
logdate=`date "+%m%d%Y"`

for logdir in `find /home/intapps -maxdepth 2 -type d -name logs -print`
do
  echo "Change Directory: ${logdir}" >> $cklog 2>&1
  cd ${logdir} >> $cklog 2>&1
  logexport=${logdir}/archive

  if [ ! -d ${logexport} ]; then
    mkdir ${logexport}
    chmod 2755 ${logexport}
  fi

  for i in `ls -1 *.[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] 2>/dev/null`; do
    if test -r $i -a -f $i; then
      echo "   Moving: ${logdir}/$i" >> $cklog
      echo "       to: ${logexport}/$i" >> $cklog 2>&1
      mv $i ${logexport}/$i >> $cklog 2>&1
      echo " Compress: ${logexport}/$i" >> $cklog 2>&1
      ${gzipexe} ${logexport}/$i >> $cklog 2>&1
      echo "" >> $cklog
    fi
  done

  echo "\nChange Directory: ${logexport}\n" >> $cklog 2>&1
  cd ${logexport} >> $cklog 2>&1

  # Next line will get all files ${oldlogs} days or older

  echo "\nPerform cleanup of old logs\n" >> $cklog

  SpaceUsed=$(df --portability . | tail -1 | awk '{print $5}' | sed 's/\%//')

  [ ${SpaceUsed} -gt 75 ] && oldlogs=${minoldlogs}

  echo "Keeping ${oldlogs} days of logs in $(pwd)"
  for i in `find . -mtime +${oldlogs} -type f`; do
    if test -r $i -a -f $i; then
      echo " Deleting: "$i >> $cklog
      rm -f $i >> $cklog 2>&1
    fi
  done
done

echo "-------------------------------------------------------------------------" >> $cklog
echo "Script Complete at `date`!" >>$cklog

# Uncomment following if you have email and want a status report
#mailx -s "${logtag} on ${host} Service Log Rotation" ${mailto} < $cklog

exit 0
